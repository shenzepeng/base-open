package com.kxg.baseopen.provider.web.response;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class FindUserPhoneAndOpenIdResponse {
    private Long userId;
    private String appId;
    private String openId;
    private String phoneNumber;
    private Boolean userExist=Boolean.FALSE;
}
