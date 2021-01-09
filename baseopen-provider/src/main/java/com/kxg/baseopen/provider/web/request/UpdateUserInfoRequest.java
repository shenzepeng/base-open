package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class UpdateUserInfoRequest {
    private String appId;
    private String openId;
    private String imgUrl;
    private String nickName;
    private String infoMsg;
    private String phoneNumber;
}
