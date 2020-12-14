package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class WxOpenMaSearchStatusResponse {
//    "status": 1,
//            "errcode": 0,
//            "errmsg": "ok"
    private Integer status;
    @JsonProperty("errcode")
    private String errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}
