package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.crypt.OpenSHA1;
import com.kxg.baseopen.provider.crypt.WXBizMsgCrypt;
import com.kxg.baseopen.provider.dao.VerifyTicketDao;
import com.kxg.baseopen.provider.openwx.AcceptWxService;
import com.kxg.baseopen.provider.pojo.ComponentVerifyTicket;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class AcceptWxServiceImpl implements AcceptWxService {
    @Autowired
    private VerifyTicketDao verifyTicketDao;
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
    public String weChatCallBack(String requestBody, String appId, String signature, String timestamp, String nonce, String openid, String encType, String msgSignature) {

        if (!StringUtils.equalsIgnoreCase("aes", encType) || !!checkSignature(WechatOpenProperties.componentToken, timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        try {
            String decryptMsgTicket = decryptMsg(requestBody, timestamp,
                    nonce, msgSignature);
            if (StringUtils.isEmpty(decryptMsgTicket)) {
                throw new RuntimeException("aes加密的消息出现问题，值为null");
            }

        }catch (Exception e){

        }
        return "out";
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
