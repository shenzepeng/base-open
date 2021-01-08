//package com.kxg.baseopen.provider.web.controller.openwx;
//
//import com.kxg.baseopen.provider.config.SzpJsonResult;
//import com.kxg.baseopen.provider.openwx.TokenService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 要写注释呀
// */
//@RequestMapping("token")
//@RestController
//public class TokenController {
//    @Autowired
//    private TokenService tokenService;
//    /**
//     * 获取最新的第三方平台的AccessToken
//     */
//    @ApiOperation("获取最新的第三方平台的AccessToken")
//    @GetMapping("component")
//    public SzpJsonResult<String> getComponentAccessToken(){
//        return SzpJsonResult.ok(tokenService.getComponentAccessToken());
//    }
//
//    /**
//     * 获取小程序的最新的accessToken
//     * @param appId
//     * @return
//     */
//    @ApiOperation("获取小程序的最新的accessToken")
//    @GetMapping("small/app")
//    public SzpJsonResult<String> getSmallAppLastAccessToken(String appId){
//        return SzpJsonResult.ok(tokenService.getSmallAppLastAccessToken(appId));
//    }
//
//}
