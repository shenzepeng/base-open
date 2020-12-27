package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

/**
 * 获取用户的openId
 */
@Data
public class GetOpenIdAndSessionKeyRequest {
    private String appId;
    private String jsCode;
}
