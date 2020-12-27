package com.kxg.baseopen.provider.openwx;

import com.kxg.baseopen.provider.dto.request.GetOpenIdAndSessionKeyRequest;
import com.kxg.baseopen.provider.dto.request.GetWxUserPhoneNumberRequest;
import com.kxg.baseopen.provider.dto.response.GetOpenIdAndSessionKeyResponse;
import com.kxg.baseopen.provider.dto.response.GetWxUserPhoneNumberResponse;

/**
 * 要写注释呀
 */
public interface OpenWxUserService {
    String GET_OPENID_SESSION_KEY="https://api.weixin.qq.com/sns/component/jscode2session";
    GetOpenIdAndSessionKeyResponse getOpenIdAndSessionKey(GetOpenIdAndSessionKeyRequest request);
    GetWxUserPhoneNumberResponse getWxUserPhoneNumber(GetWxUserPhoneNumberRequest request);
}
