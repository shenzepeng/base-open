package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class UpdateWxServerAddressResponse {
    /**
     * {
     *   "errcode": 0,
     *   "errmsg": "ok",
     *   "requestdomain": ["https://www.qq.com", "https://www.qq.com"],
     *   "wsrequestdomain": ["wss://www.qq.com", "wss://www.qq.com"],
     *   "uploaddomain": ["https://www.qq.com", "https://www.qq.com"],
     *   "downloaddomain": ["https://www.qq.com", "https://www.qq.com"]
     * }
     */

    private Integer errcode;
    private String errmsg;
    private List<String> requestdomain;

    private List<String> wsrequestdomain;

    private List<String> uploaddomain;
    private List<String> downloaddomain;
}
