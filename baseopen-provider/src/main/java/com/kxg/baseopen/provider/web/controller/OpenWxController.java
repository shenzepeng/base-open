package com.kxg.baseopen.provider.web.controller;

import com.kxg.baseopen.provider.mapper.SaveCallInfoMapper;
import com.kxg.baseopen.provider.pojo.SaveCallInfo;
import com.kxg.baseopen.provider.service.OpenWxService;
import com.kxg.baseopen.provider.service.impl.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 要写注释呀
 */
@Slf4j
@RestController
@RequestMapping("public/wx")
public class OpenWxController {
    @Autowired
    private OpenWxService openWxService;
    @Autowired
    private MailService mailService;
    /**
     * 获取开放平台的ticket
     * @param requestBody xml信息
     * @param timestamp 时间戳
     * @param nonce
     * @param signature  签名
     * @param encType 加密类型
     * @param msgSignature  信息签名
     * @return
     */
    @PostMapping("component/ticket")
    public Object receiveTicket(@RequestBody(required = false) String requestBody, @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce, @RequestParam("signature") String signature,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        List<String> recivers = mailService.getRecivers();
        StringBuilder sb=new StringBuilder();
        sb.append("接收微信请求：");
        sb.append("signature="+signature);
        sb.append("\n");
        sb.append("encType="+encType);
        sb.append("\n");
        sb.append("msgSignature="+msgSignature);
        sb.append("\n");
        sb.append("timestamp="+timestamp);
        sb.append("\n");
        sb.append("nonce="+nonce);
        sb.append("\n");
        sb.append("requestBody="+requestBody);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                mailService.sent(recivers,"接收微信请求",sb.toString());
            }
        });
        t1.start();
        log.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        return openWxService.receiverVerifyTicket(requestBody, timestamp,
                nonce,signature,encType,msgSignature);
    }

    @Autowired
    private SaveCallInfoMapper saveCallInfoMapper;
    @RequestMapping("{appId}/callback")
    public Object callback(@RequestBody(required = false) String requestBody,
                           @PathVariable("appId") String appId,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("openid") String openid,
                           @RequestParam("encrypt_type") String encType,
                           @RequestParam("msg_signature") String msgSignature) {
       log.info("\n接收微信请求：[appId=[{}], openid=[{}], signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                appId, openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
        SaveCallInfo saveCallInfo=new SaveCallInfo();
        saveCallInfo.setAppId(appId);
        saveCallInfo.setEnctype(encType);
        saveCallInfo.setMsgsignature(msgSignature);
        saveCallInfo.setNonce(nonce);
        saveCallInfo.setOpenid(openid);
        saveCallInfo.setRequestBody(requestBody);
        saveCallInfo.setTimestamp(timestamp);
        saveCallInfo.setSignature(signature);
        saveCallInfoMapper.insertSelective(saveCallInfo);
        return "";
    }



}
