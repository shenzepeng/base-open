package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class AppDomainResponse {
    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;
    @JsonProperty("requestdomain")
    private List<String> requestdomainList;

    @JsonProperty("wsrequestdomain")
    private List<String> wsrequestdomainList;

    @JsonProperty("uploaddomain")
    private List<String> uploaddomainList;

    @JsonProperty("downloaddomain")
    private List<String> downloaddomainList;
}
