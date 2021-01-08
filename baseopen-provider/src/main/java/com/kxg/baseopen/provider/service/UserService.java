package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.web.request.FindUserPhoneAndOpenIdRequest;
import com.kxg.baseopen.provider.web.response.FindUserPhoneAndOpenIdResponse;

/**
 * 要写注释呀
 */
public interface UserService {
    /**
     * 通过手机号查找用户
     * 没有当前手机号新增
     * @param phoneNumber
     */
    void findUserInfo(String phoneNumber);


    FindUserPhoneAndOpenIdResponse findUserInfo(FindUserPhoneAndOpenIdRequest request);
}
