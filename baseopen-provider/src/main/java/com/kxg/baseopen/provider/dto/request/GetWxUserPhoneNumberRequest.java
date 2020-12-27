package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetWxUserPhoneNumberRequest {
    private String appId;
    //微信加密信息，通过该信息获取手机号
    private String encrytedData;
    private String sessionKey;
    /**
     * 加密算法的初始向量
     */
    private String iv;
}
