package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.pojo.FastRegister;

import java.util.List;

/**
 * 快速认证第三方相关业务
 */
public interface FastRegisterService {
    void addFastRegister(FastRegister register);
    void updateFastRegister(FastRegister fastRegister);
    List<FastRegister> findFastRegister(String code);
}
