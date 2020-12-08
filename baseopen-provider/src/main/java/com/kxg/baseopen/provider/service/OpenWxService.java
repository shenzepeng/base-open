package com.kxg.baseopen.provider.service;

import me.chanjar.weixin.open.api.WxOpenComponentService;

/**
 * 获取ticket和access token
 */
public interface OpenWxService {
    String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    /**
     * 获取开放平台的ticket
     *
     * @param requestBody  xml信息
     * @param timestamp    时间戳
     * @param nonce
     * @param signature    签名
     * @param encType      加密类型
     * @param msgSignature 信息签名
     * @return
     */
    String receiverVerifyTicket(String requestBody, String timestamp,
                                String nonce, String signature, String encType, String msgSignature);

    /**
     * 获取最新的accessToken
     */
    String getAccessToken();

    /**
     *  微信事件推送url
     * @param requestBody
     * @param appId
     * @param signature
     * @param timestamp
     * @param nonce
     * @param openid
     * @param encType
     * @param msgSignature
     * @return
     */
    String weChatCallBack(String requestBody,
                          String appId,
                          String signature,
                          String timestamp,
                          String nonce,
                          String openid,
                          String encType,
                          String msgSignature);

}
