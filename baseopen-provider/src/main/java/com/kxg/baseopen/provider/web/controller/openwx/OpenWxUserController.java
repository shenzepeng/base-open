package com.kxg.baseopen.provider.web.controller.openwx;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.dto.request.GetOpenIdAndSessionKeyRequest;
import com.kxg.baseopen.provider.dto.request.GetWxUserPhoneNumberRequest;
import com.kxg.baseopen.provider.dto.response.GetOpenIdAndSessionKeyResponse;
import com.kxg.baseopen.provider.dto.response.GetWxUserPhoneNumberResponse;
import com.kxg.baseopen.provider.openwx.OpenWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@Api("获取openid和手机号")
@RequestMapping("open/user")
@RestController
public class OpenWxUserController {
    @Autowired
    private OpenWxUserService openWxUserService;
    @ApiOperation("通过code获取openid和session key")
    @PostMapping("getOpenIdAndSessionKey")
    public SzpJsonResult<GetOpenIdAndSessionKeyResponse> getOpenIdAndSessionKey(@RequestBody GetOpenIdAndSessionKeyRequest request){
        return SzpJsonResult.ok(openWxUserService.getOpenIdAndSessionKey(request));
    }
    @ApiOperation("通过session key和密钥获取手机号")
    @PostMapping("getWxUserPhoneNumber")
    public SzpJsonResult<GetWxUserPhoneNumberResponse> getWxUserPhoneNumber(@RequestBody GetWxUserPhoneNumberRequest request){
        return SzpJsonResult.ok(openWxUserService.getWxUserPhoneNumber(request));
    }

}
