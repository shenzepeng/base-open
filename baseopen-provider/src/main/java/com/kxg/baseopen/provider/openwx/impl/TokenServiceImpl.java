package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.crypt.OpenSHA1;
import com.kxg.baseopen.provider.crypt.WXBizMsgCrypt;
import com.kxg.baseopen.provider.dao.AuthorAccessTokenDao;
import com.kxg.baseopen.provider.dao.AuthorizerInfoDao;
import com.kxg.baseopen.provider.dao.OpenWxAccessTokenDao;
import com.kxg.baseopen.provider.dao.VerifyTicketDao;
import com.kxg.baseopen.provider.dto.getappaccesstoken.AppAccessToken;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.pojo.AuthorizerInfo;
import com.kxg.baseopen.provider.pojo.AuthorizerToken;
import com.kxg.baseopen.provider.pojo.OpenWxAccessToken;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
public class TokenServiceImpl implements TokenService {
    @Autowired
    private AuthorAccessTokenDao authorAccessTokenDao;
    @Autowired
    private AuthorizerInfoDao authorizerInfoDao;
    @Autowired
    private VerifyTicketDao verifyTicketDao;
    @Autowired
    private OpenWxAccessTokenDao openWxAccessTokenDao;
    @Override
    public String getComponentAccessToken() {
        List<OpenWxAccessToken> lastAccessToken = openWxAccessTokenDao.getLastAccessToken();
        if (CollectionUtils.isEmpty(lastAccessToken)) {
            String componentAccessToken = getComponentToken();
            log.info("componentAccessToken {}",componentAccessToken);
            addNewAccessToken(componentAccessToken);
            return componentAccessToken;
        }
        OpenWxAccessToken openWxAccessToken = lastAccessToken.get(0);
        if (Long.parseLong(openWxAccessToken.getExpirationTime())<System.currentTimeMillis()){
            String componentAccessToken = getComponentToken();
            log.info("componentAccessToken {}",componentAccessToken);
            addNewAccessToken(componentAccessToken);
        }
        return openWxAccessToken.getAccessToken();
    }

    private void  addNewAccessToken(String accessToken){
        OpenWxAccessToken openWxAccessToken=new OpenWxAccessToken();
        openWxAccessToken.setAccessToken(accessToken);
        Long expirationTime = System.currentTimeMillis() + 60 * 1000 * 110;
        openWxAccessToken.setExpirationTime(expirationTime.toString());
        openWxAccessTokenDao.addAccessToken(openWxAccessToken);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getSmallAppLastAccessToken(String appId) {
        List<AuthorizerToken> lastAuthorToken = authorAccessTokenDao.findLastAuthorToken(appId);
        List<AuthorizerInfo> lastAppIdInfo = authorizerInfoDao.findLastAppIdInfo(appId);
        if (CollectionUtils.isEmpty(lastAppIdInfo)){
            throw new RuntimeException("该商户未授权");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid", WechatOpenProperties.componentAppId);
        map.put("authorizer_appid",lastAppIdInfo.get(0).getAuthorizerAppid());
        map.put("authorizer_refresh_token",lastAppIdInfo.get(0).getAuthorizerRefreshToken());
        String targetUrl=GET_APP_LAST_ACCESS_TOKEN+"?component_access_token="+ getComponentAccessToken();
        if (CollectionUtils.isEmpty(lastAuthorToken)||Long.parseLong(lastAuthorToken.get(0).getExpiredTime())<System.currentTimeMillis()){
            String postInfo = postInfo(targetUrl,map,null);
            AppAccessToken appAccessToken = JsonUtils.toBean(postInfo, AppAccessToken.class);
            log.info("AppAccessToken {}",appAccessToken);
            save(appAccessToken,appId);
            return appAccessToken.getAuthorizerAccessToken();
        }
        return lastAuthorToken.get(0).getAuthorizerAccessToken();
    }
    /**
     * 获取小程序最新的
     * @param appAccessToken
     * @param appId
     */
    private void save(AppAccessToken appAccessToken,String appId){
        AuthorizerToken authorizerToken=new AuthorizerToken();
        Long expirationTime = System.currentTimeMillis() + 30 * 1000 * 110;
        authorizerToken.setAuthorizerAppid(appId);
        authorizerToken.setExpiredTime(expirationTime.toString());
        authorizerToken.setAuthorizerAppid(authorizerToken.getAuthorizerAppid());
        authorizerToken.setAuthorizerAccessToken(appAccessToken.getAuthorizerAccessToken());
        authorAccessTokenDao.addAuthorToken(authorizerToken);

        AuthorizerInfo authorizerInfo=new AuthorizerInfo();
        authorizerInfo.setAuthorizerAppid(appId);
        authorizerInfo.setAuthorizerRefreshToken(appAccessToken.getAuthorizerRefreshToken());
        authorizerInfoDao.add(authorizerInfo);
    }



//    private void  addNewAccessToken(String accessToken){
//        OpenWxAccessToken openWxAccessToken=new OpenWxAccessToken();
//        openWxAccessToken.setAccessToken(accessToken);
//        Long expirationTime = System.currentTimeMillis() + 60 * 1000 * 110;
//        openWxAccessToken.setExpirationTime(expirationTime.toString());
//        openWxAccessTokenDao.addAccessToken(openWxAccessToken);
//    }
    private String getComponentToken()  {
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

    private String postInfo(String targetUrl, Map<String, Object> bodyMsg,  HashMap<String, String> header) {
            return HttpClientUtil.postJson(targetUrl, bodyMsg, header);
        }

}
