package com.kxg.baseopen.provider.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.common.ReturnCode;
import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.pojo.SmsInfo;
import com.kxg.baseopen.provider.utils.JsonUtils;
import com.kxg.baseopen.provider.web.request.SentSmsRequest;
import com.kxg.baseopen.provider.web.request.VerifyRequest;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 要写注释呀
 */
public interface SmsService {
    SzpJsonResult<IntegerResult> sent(SentSmsRequest sentSmsRequest) ;

    SzpJsonResult<IntegerResult> verify(VerifyRequest request);


}