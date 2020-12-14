package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class BindTesterRequest {
    private String appId;
    /**
     * 被绑定者微信号
     */
    private String weChatId;
}
