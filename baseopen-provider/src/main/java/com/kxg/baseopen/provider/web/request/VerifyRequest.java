package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class VerifyRequest {
    private String code;
    private String phoneNumber;
}
