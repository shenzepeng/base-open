package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class ChangeWxaSearchStatusResponse {
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}
