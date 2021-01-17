package com.kxg.baseopen.provider.web.controller;

import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.crypt.WXBizMsgCrypt;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
@RestController
@RequestMapping("health")
public class HealthController {
    @SneakyThrows
    @GetMapping
    public String get(){
        //
        // 第三方回复公众平台
        //
        // 需要加密的明文
        String encodingAesKey = "2fd120fa7e7144228025acedee461ca25cbc33e79b1";
        String token = "QWEASD";
        String appId = "wxf0c90a7b72ebbc7c";

        String timestamp = "1607048862";
        String nonce = "567612888";
        String msgSignature = "989b3853c29d8323d64559d60e72460a08d0bc6c";

        String xml = "<xml>\n" +
                "    <AppId><![CDATA[wxf0c90a7b72ebbc7c]]></AppId>\n" +
                "    <Encrypt><![CDATA[MaoljKuAZ3Qsso9cEWXDJpulHDGjUVHE3GDDKCx2CLKnFIUbD2jNYVHUyT0+T789XTNbHe1nMTZkr0BK8YSVfQA9f01ektf4LrnfBRZ5MjD9LOnGSxSSFrrYQbPYwD5ZMY3QpNnYkzPhOq4uCROTUzAbyYVBVGWIdgyNyvCm+ovhEXK0+yYQ+ZlbvDPydJ1vVcZPRbD+hhusr90k4ZZJNKvXzrG+wBx4gSIvNZ1hjLX2NnxcdU7zIHah9r2gjHPvWlXshbIurlQSHjw84in1fB0esTiSMs0dZygzguTLp76Uwa7oDa6VcNFtB2FAUISQ/h9yIzGQfuqVjW4IMup6L7rNe9caz//zTaETBkXDfgxc1Wfbktug25jBTb/1KcLEAF5AqdYRKJ9wmeZOJxlU8DUW+Z5m4znpDSOSNNpzGJsi1m5mPE6DiIr8B5zm2pHTWZChbzcFq+haW5Y55/1iWA==]]></Encrypt>\n" +
                "</xml>";
        //
        // 公众平台发送消息给第三方，第三方处理
        //
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
        System.out.println("解密后明文: " + result2);

        Map<String, String> fromXml = xmlToMap(result2);
        log.info("decryptMsg fromXml {}", fromXml);
        return fromXml.toString();
    }
    @SneakyThrows
    @PostMapping
    public String receiveTicket(@RequestBody(required = false) String requestBody, @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce, @RequestParam("signature") String signature,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(WechatOpenProperties.componentToken, WechatOpenProperties.componentAesKey, WechatOpenProperties.componentAppId);
        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, requestBody);
        System.out.println("解密后明文: " + result2);

        Map<String, String> fromXml = xmlToMap(result2);
        log.info("decryptMsg {}", fromXml);
        return fromXml.toString();
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
}
