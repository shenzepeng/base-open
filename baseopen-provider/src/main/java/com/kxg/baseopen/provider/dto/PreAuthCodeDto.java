package com.kxg.baseopen.provider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 预售权码
 */
@Data
public class PreAuthCodeDto {
    @JsonProperty("pre_auth_code")
    private String preAuthCode;
}
