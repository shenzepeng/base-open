package com.kxg.baseopen.provider.openwx;

/**
 * 预售权相关逻辑
 */
public interface AuthorizationService {
    /**
     * 预售权码
     */
    String PRE_AUTH_CODE="https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
    /**
     * 生成客户确认的URL
     */
    String MAKE_SURE_URL="https://mp.weixin.qq.com/safe/bindcomponent?action=bindcomponent&no_scan=1";

    /**
     * 获取用户授权小程序
     * 的扫码二维码
     */
    String SCAN_QR_CODE_URL="https://mp.weixin.qq.com/cgi-bin/componentloginpage";
    /**
     * 通过微信回调
     * auth_code 获取微信的
     * refresh token
     */
    String GET_APP_REFRESH_CODE="https://api.weixin.qq.com/cgi-bin/component/api_query_auth";


    /**
     * 获取客户授权的链接
     * @param appId
     * @return
     */
    String getCustomerMakeSureUrl(String appId,String useQrCode);

    /**
     * 微信请求的callbackurl
     * 就是通过授权连接 redirect uri
     * @param authCode
     * @param expiredTime
     * @return
     */
    String getCallBackUrl(String authCode,Integer  expiredTime);
}
