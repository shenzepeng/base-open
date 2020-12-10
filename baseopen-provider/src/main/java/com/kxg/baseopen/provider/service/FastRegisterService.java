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

    /**
     * 生成预售权码
     * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/api/pre_auth_code.html
     * @return
     */

}
