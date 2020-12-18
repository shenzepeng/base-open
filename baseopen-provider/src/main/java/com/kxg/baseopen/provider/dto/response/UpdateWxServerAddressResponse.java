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
    @JsonProperty("requestdomain")
    private List<String> requestDomain;
    @JsonProperty("wsrequestdomain")
    private List<String> wsRequestDomain;
    @JsonProperty("uploaddomain")
    private List<String> uploadDomain;
    @JsonProperty("downloaddomain")
    private List<String> downloadDomain;
}
