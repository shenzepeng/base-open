package com.kxg.baseopen.provider.openwx;

/**
 * 获取token service
 */
public interface TokenService {
    String API_COMPONENT_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
    String GET_APP_LAST_ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=";

    /**
     * 获取最新的第三方平台的AccessToken
     */
    String getComponentAccessToken();

    /**
     *
     */
    /**
     * 获取小程序的最新的accessToken
     * @param appId
     * @return
     */
    String getSmallAppLastAccessToken(String appId);

}
