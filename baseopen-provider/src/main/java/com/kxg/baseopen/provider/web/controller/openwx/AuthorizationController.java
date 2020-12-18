package com.kxg.baseopen.provider.web.controller.openwx;

import com.kxg.baseopen.provider.openwx.AuthorizationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 要写注释呀
 */
@RequestMapping("authorization")
@RestController
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
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
    @ApiOperation("获取客户授权的链接")
    @GetMapping("costomer/make")
    public String getCustomerMakeSureUrl(@RequestParam(required = false) String appId,@RequestParam(required = false) String useQrCode){
        return authorizationService.getCustomerMakeSureUrl(appId,useQrCode);
    }

    /**
     * 微信请求的callbackurl
     * 就是通过授权连接 redirect uri
     * @param authCode
     * @param expiredTime
     * @return
     */
    @ApiOperation("微信请求的callbackurl")
    @GetMapping("call/back/url")
    public String getCallBackUrl(@RequestParam(value = "auth_code") String authCode, @RequestParam("expires_in") Integer  expiredTime){
        return authorizationService.getCallBackUrl(authCode,expiredTime);
    }
}
