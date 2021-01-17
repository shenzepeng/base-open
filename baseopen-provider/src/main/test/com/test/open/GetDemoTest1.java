package com.test.open;

import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.crypt.OpenSHA1;
import com.kxg.baseopen.provider.crypt.WXBizMsgCrypt;
import com.kxg.baseopen.provider.mapper.SaveCallInfoMapper;
import com.kxg.baseopen.provider.openwx.AcceptWxService;
import com.kxg.baseopen.provider.pojo.SaveCallInfo;
import com.kxg.baseopen.provider.service.impl.MailService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.kxg.baseopen.provider.DubboProviderBootstrap.class)
public class GetDemoTest1 {
    @Autowired
    private AcceptWxService acceptWxService;
    @Autowired
    private MailService mailService;
    @Autowired
    private SaveCallInfoMapper saveCallInfoMapper;

    @SneakyThrows
    @Test
    public void getPreCode() {
        List<SaveCallInfo> saveCallInfos = saveCallInfoMapper.selectAll();
        SaveCallInfo saveCallInfo = saveCallInfos.get(0);
        for (SaveCallInfo callInfo : saveCallInfos) {
            String weChatCallBack = acceptWxService.weChatCallBack(saveCallInfo.getRequestBody(),
                    saveCallInfo.getAppId(),
                    saveCallInfo.getSignature(),
                    saveCallInfo.getTimestamp(),
                    saveCallInfo.getNonce(),
                    saveCallInfo.getOpenid(),
                    saveCallInfo.getEnctype(),
                    saveCallInfo.getMsgsignature());
            System.out.println(weChatCallBack);
        }

        if (!StringUtils.equalsIgnoreCase("aes", saveCallInfo.getEnctype()) || !checkSignature(WechatOpenProperties.componentToken, saveCallInfo.getTimestamp(), saveCallInfo.getNonce(), saveCallInfo.getSignature())) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        String out = "";
        Map<String, String> stringStringMap = decryptMsgGetMap(saveCallInfo.getRequestBody(), saveCallInfo.getTimestamp(),
                saveCallInfo.getNonce(), saveCallInfo.getMsgsignature());
        System.out.println(stringStringMap);
        if (CollectionUtils.isEmpty(stringStringMap)) {
            throw new RuntimeException("aes加密的消息出现问题，值为null");
        }


//        // aes加密的消息
//        WxMpXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedMpXml(requestBody,
//                wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
//        this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
//        // 全网发布测试用例
//        if (StringUtils.equalsAnyIgnoreCase(appId, "wxd101a85aa106f53e", "wx570bc396a51b8ff8")) {
//            try {
//                if (StringUtils.equals(inMessage.getMsgType(), "text")) {
//                    if (StringUtils.equals(inMessage.getContent(), "TESTCOMPONENT_MSG_TYPE_TEXT")) {
//                        out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(
//                                WxMpXmlOutMessage.TEXT().content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
//                                        .fromUser(inMessage.getToUser())
//                                        .toUser(inMessage.getFromUser())
//                                        .build(),
//                                wxOpenService.getWxOpenConfigStorage()
//                        );
//                    } else if (StringUtils.startsWith(inMessage.getContent(), "QUERY_AUTH_CODE:")) {
//                        String msg = inMessage.getContent().replace("QUERY_AUTH_CODE:", "") + "_from_api";
//                        WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(msg).toUser(inMessage.getFromUser()).build();
//                        wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
//                    }
//                } else if (StringUtils.equals(inMessage.getMsgType(), "event")) {
//                    WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(inMessage.getEvent() + "from_callback").toUser(inMessage.getFromUser()).build();
//                    wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
//                }
//            } catch (WxErrorException e) {
//                logger.error("callback", e);
//            }
//        }else{
//            WxMpXmlOutMessage outMessage = wxOpenService.getWxOpenMessageRouter().route(inMessage, appId);
//            if(outMessage != null){
//                out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(outMessage, wxOpenService.getWxOpenConfigStorage());
//            }
//        }
    }

    private String decryptMsg(String requestBody, String timestamp,
                              String nonce, String msgSignature) throws Exception {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WechatOpenProperties.componentToken, WechatOpenProperties.componentAesKey, WechatOpenProperties.componentAppId);
        // 第三方收到公众号平台发送的消息
        String decryptMsg = pc.decryptMsg(msgSignature, timestamp, nonce, requestBody);
        log.info("decryptMsg pc.decryptMsg {}", decryptMsg);
        Map<String, String> fromXml = xmlToMap(decryptMsg);
        return fromXml.get("ComponentVerifyTicket");
    }

    private Map<String, String> decryptMsgGetMap(String requestBody, String timestamp,
                              String nonce, String msgSignature) throws Exception {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WechatOpenProperties.componentToken, WechatOpenProperties.componentAesKey, WechatOpenProperties.componentAppId);
        // 第三方收到公众号平台发送的消息
        String decryptMsg = pc.decryptMsg(msgSignature, timestamp, nonce, requestBody);
        log.info("decryptMsg pc.decryptMsg {}", decryptMsg);
        Map<String, String> fromXml = xmlToMap(decryptMsg);
        return fromXml;
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
