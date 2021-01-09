package com.kxg.baseopen.provider.web.controller.user;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.UserService;
import com.kxg.baseopen.provider.web.request.AddUserInfoRequest;
import com.kxg.baseopen.provider.web.request.FindUserPhoneAndOpenIdRequest;
import com.kxg.baseopen.provider.web.request.UpdateUserInfoRequest;
import com.kxg.baseopen.provider.web.response.FindUserPhoneAndOpenIdResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation("获取用户信息，通过手机号，或者openId，换取userID")
    @PostMapping("findUserInfo")
    private SzpJsonResult<FindUserPhoneAndOpenIdResponse> findUserInfo(@RequestBody FindUserPhoneAndOpenIdRequest request){
        return SzpJsonResult.ok(userService.findUserInfo(request));
    }
    @ApiOperation("更新用户信息的接口,输出   appId  openId   头像   昵称   地区   输出更新成功还是失败")
    @PostMapping("updateUser")
    public SzpJsonResult<IntegerResult> updateUser(@RequestBody UpdateUserInfoRequest request){
        return SzpJsonResult.ok(userService.updateUserInfo(request));
    }
    @ApiOperation("添加用户")
    @PostMapping("addUser")
    public SzpJsonResult<IntegerResult> addUser(@RequestBody AddUserInfoRequest request){
        return SzpJsonResult.ok(userService.addUserInfo(request));
    }
}
