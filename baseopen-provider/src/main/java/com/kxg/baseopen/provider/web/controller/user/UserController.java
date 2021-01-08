package com.kxg.baseopen.provider.web.controller.user;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.UserService;
import com.kxg.baseopen.provider.web.request.FindUserPhoneAndOpenIdRequest;
import com.kxg.baseopen.provider.web.response.FindUserPhoneAndOpenIdResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("获取用户信息，通过手机号，或者openId，换取userID")
    @PostMapping("findUserInfo")
    private SzpJsonResult<FindUserPhoneAndOpenIdResponse> findUserInfo(@RequestBody FindUserPhoneAndOpenIdRequest request){
        return SzpJsonResult.ok(userService.findUserInfo(request));
    }
}
