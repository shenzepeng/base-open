package com.kxg.baseopen.provider.service.impl;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kxg.baseopen.provider.common.WechatOpenProperties;;
import com.kxg.baseopen.provider.crypt.OpenSHA1;
import com.kxg.baseopen.provider.crypt.WXBizMsgCrypt;
import com.kxg.baseopen.provider.dao.OpenWxAccessTokenDao;
import com.kxg.baseopen.provider.dao.VerifyTicketDao;
import com.kxg.baseopen.provider.pojo.ComponentVerifyTicket;
import com.kxg.baseopen.provider.pojo.OpenWxAccessToken;
import com.kxg.baseopen.provider.service.OpenWxService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenFastMaService;
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.bean.WxOpenCreateResult;
import me.chanjar.weixin.open.bean.WxOpenGetResult;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class OpenWxServiceImpl implements OpenWxService {
    @Autowired
    private VerifyTicketDao verifyTicketDao;
    @Autowired
    private OpenWxAccessTokenDao openWxAccessTokenDao;

    @Override
    public String receiverVerifyTicket(String requestBody, String timestamp,
                                       String nonce, String signature, String encType, String msgSignature) {
        log.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);
        if (!StringUtils.equalsIgnoreCase("aes", encType)
                || !checkSignature(WechatOpenProperties.componentToken, timestamp, nonce, signature)) {
            log.error("非法请求，可能属于伪造的请求！");
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        try {
            // aes加密的消息
            String decryptMsgTicket = decryptMsg(requestBody, timestamp,
                    nonce, msgSignature);
            if (StringUtils.isEmpty(decryptMsgTicket)) {
                throw new RuntimeException("aes加密的消息出现问题，值为null");
            }
            ComponentVerifyTicket componentVerifyTicket = new ComponentVerifyTicket();
            componentVerifyTicket.setComponentVerifyTicket(decryptMsgTicket);
            Long expirationTime = System.currentTimeMillis() + 30 * 1000 * 110;
            componentVerifyTicket.setExpirationTime(expirationTime.toString());
            verifyTicketDao.addNewComponent(componentVerifyTicket);
        } catch (Exception e) {
            log.error("exception", e);
        }
        return "success";
    }



    @Override
    public String getAccessToken() {
        List<OpenWxAccessToken> lastAccessToken = openWxAccessTokenDao.getLastAccessToken();
        if (CollectionUtils.isEmpty(lastAccessToken)) {
            String componentAccessToken = getComponentAccessToken();
            addNewAccessToken(componentAccessToken);
            return componentAccessToken;
        }
        OpenWxAccessToken openWxAccessToken = lastAccessToken.get(0);
        if (Long.parseLong(openWxAccessToken.getExpirationTime())<System.currentTimeMillis()){
            String componentAccessToken = getComponentAccessToken();
            addNewAccessToken(componentAccessToken);
        }
        return openWxAccessToken.getAccessToken();
    }
    private void  addNewAccessToken(String accessToken){
        OpenWxAccessToken openWxAccessToken=new OpenWxAccessToken();
        openWxAccessToken.setAccessToken(accessToken);
        Long expirationTime = System.currentTimeMillis() + 30 * 1000 * 110;
        openWxAccessToken.setExpirationTime(expirationTime.toString());
        openWxAccessTokenDao.addAccessToken(openWxAccessToken);
    }
    private String getComponentAccessToken()  {
        HashMap<String, String> componentMsgHap = new HashMap<>();
        componentMsgHap.put("component_appid", WechatOpenProperties.componentAppId);
        componentMsgHap.put("component_appsecret", WechatOpenProperties.componentSecret);
        componentMsgHap.put("component_verify_ticket", verifyTicketDao.findLast().get(0).getComponentVerifyTicket());
        String accessToken = HttpClientUtil.postJson(API_COMPONENT_TOKEN_URL, componentMsgHap);
        WxOpenComponentAccessToken componentAccessToken = WxOpenComponentAccessToken.fromJson(accessToken);
        log.info("WxOpenComponentAccessToken {}", JsonUtils.convertObjectToJSON(componentAccessToken));
        return componentAccessToken.getComponentAccessToken();
    }

    private String decryptMsg(String requestBody, String timestamp,
                              String nonce, String msgSignature) throws Exception {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WechatOpenProperties.componentToken, WechatOpenProperties.componentAesKey, WechatOpenProperties.componentAppId);
        // 第三方收到公众号平台发送的消息
        String decryptMsg = pc.decryptMsg(msgSignature, timestamp, nonce, requestBody);
        log.info("decryptMsg {}", decryptMsg);
        Map<String, String> fromXml = xmlToMap(decryptMsg);
        return fromXml.get("ComponentVerifyTicket");
    }

    private static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }

    }

    private boolean checkSignature(String componentToken, String timestamp, String nonce, String signature) {
        try {
            return OpenSHA1.gen(componentToken, timestamp, nonce)
                    .equals(signature);
        } catch (Exception e) {
            log.error("Checking signature failed, and the reason is :" + e.getMessage());
            return false;
        }
    }
}
