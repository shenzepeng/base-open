package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置小程序名称
 */
@Data
public class SettingWxNameResponse {
    private String errcode;
    private String errmsg;
    private String wording;
    @JsonProperty("audit_id")
    private String auditId;
}
