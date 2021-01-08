package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class FindUserPhoneAndOpenIdRequest {
    private String appId;
    private String openId;
    private String phoneNumber;
}
