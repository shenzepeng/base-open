package com.kxg.baseopen.provider.openwx.impl;

import com.alibaba.fastjson.JSONObject;
import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.dto.getphonenumber.GetPhoneNumberDto;
import com.kxg.baseopen.provider.dto.request.GetOpenIdAndSessionKeyRequest;
import com.kxg.baseopen.provider.dto.request.GetWxUserPhoneNumberRequest;
import com.kxg.baseopen.provider.dto.response.GetOpenIdAndSessionKeyResponse;
import com.kxg.baseopen.provider.dto.response.GetWxUserPhoneNumberResponse;
import com.kxg.baseopen.provider.openwx.OpenWxUserService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.AESUtils;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
/**
 * 要写注释呀
 */
@Slf4j
@Service
public class OpenWxUserServiceImpl implements OpenWxUserService {
    @Autowired
    private TokenService tokenService;
    @Override
    public GetOpenIdAndSessionKeyResponse getOpenIdAndSessionKey(GetOpenIdAndSessionKeyRequest request) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(GET_OPENID_SESSION_KEY);
        stringBuilder.append("?appid="+request.getAppId());
        stringBuilder.append("&js_code="+request.getJsCode());
        stringBuilder.append("&grant_type=authorization_code");
        stringBuilder.append("&component_appid="+ WechatOpenProperties.componentAppId);
        stringBuilder.append("&component_access_token="+tokenService.getComponentAccessToken());
        String getInfo=getInfo(stringBuilder.toString(), null);
        return JsonUtils.toBean(getInfo, GetOpenIdAndSessionKeyResponse.class);
    }
    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }
    @SneakyThrows
    @Override
    public GetWxUserPhoneNumberResponse getWxUserPhoneNumber(GetWxUserPhoneNumberRequest request) {
        GetWxUserPhoneNumberResponse response=new GetWxUserPhoneNumberResponse();
        //AESUtils微信获取手机号解密工具类
        AESUtils aes = new AESUtils();
        //调用AESUtils工具类decrypt方法解密获取json串
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(request.getEncrytedData()), Base64.decodeBase64(request.getSessionKey()),
                Base64.decodeBase64(request.getIv()));
        //判断返回参数是否为空
        if (null != resultByte && resultByte.length > 0) {
            String jsons = new String(resultByte, "UTF-8");
            GetPhoneNumberDto getPhoneNumberDto = JsonUtils.toBean(jsons, GetPhoneNumberDto.class);
            response.setGetPhoneNumberDto(getPhoneNumberDto);
            return response;
        }
        return response;
    }
}
