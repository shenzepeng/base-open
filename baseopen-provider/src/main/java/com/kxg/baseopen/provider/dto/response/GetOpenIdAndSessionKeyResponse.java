package com.kxg.baseopen.provider.dto.response;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetOpenIdAndSessionKeyResponse {
    /**
     * {
     *   "openid": "OPENID",
     *   "session_key": "SESSIONKEY"
     * }
     * {
     *   "errcode": "40029",
     *   "errmsg": "invalid code"
     * }
     */
    private String openid;
    private String session_key;
    private String errcode;
    private String errmsg;
}
