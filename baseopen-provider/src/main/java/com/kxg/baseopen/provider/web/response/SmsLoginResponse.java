package com.kxg.baseopen.provider.web.response;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class SmsLoginResponse {
    private Long userId;
    private String appId;
    private String openId;
    /**
     * 状态 0客户 1商户 2代理 3销售
     */
    private Integer status;
    /**
     * 是否创建了小程序
     */
    private Boolean hadCreateApp=false;
}
