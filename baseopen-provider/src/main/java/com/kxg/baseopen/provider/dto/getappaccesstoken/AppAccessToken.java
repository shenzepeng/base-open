package com.kxg.baseopen.provider.dto.getappaccesstoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class AppAccessToken {
    @JsonProperty("authorizer_access_token")
    private String  authorizerAccessToken;
    @JsonProperty("expires_in")
    private String  expiresIn;
    @JsonProperty("authorizer_refresh_token")
    private String  authorizerRefreshToken;
}
