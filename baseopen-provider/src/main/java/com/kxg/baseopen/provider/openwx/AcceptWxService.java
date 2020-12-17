package com.kxg.baseopen.provider.openwx;

/**
 * 接受微信请求
 */
public interface AcceptWxService {
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
