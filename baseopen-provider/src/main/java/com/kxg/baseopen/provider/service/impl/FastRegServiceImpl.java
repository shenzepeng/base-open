package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.WxFastRegisterDao;
import com.kxg.baseopen.provider.openwx.FastAttestationService;
import com.kxg.baseopen.provider.pojo.WxFastRegister;
import com.kxg.baseopen.provider.service.FastRegService;
import com.kxg.baseopen.provider.web.request.AddLicenseRequest;
import com.kxg.baseopen.provider.web.request.FastRegisterRequest;
import com.kxg.baseopen.provider.web.response.AddLicebseResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class FastRegServiceImpl implements FastRegService {
    @Autowired
    private FastAttestationService fastAttestationService;
    @Autowired
    private WxFastRegisterDao wxFastRegisterDao;
    private static final String componentPhone="18049240453";


    @Override
    public AddLicebseResponse addLicense(AddLicenseRequest request) {
        //微信认证
        FastRegisterRequest registerRequest=new FastRegisterRequest();
        registerRequest.setCode(registerRequest.getCode());
        registerRequest.setCodeType(registerRequest.getCodeType());
        registerRequest.setComponentPhone(registerRequest.getComponentPhone());
        registerRequest.setLegalPersonaWechat(registerRequest.getLegalPersonaWechat());
        registerRequest.setLegalPersonaName(registerRequest.getLegalPersonaName());
        registerRequest.setComponentPhone(componentPhone);
        WxOpenResult wxOpenResult = fastAttestationService.fastRegisterWeapp(registerRequest);

        WxFastRegister wxFastRegister=new WxFastRegister();
        BeanUtils.copyProperties(request,wxFastRegister);
        wxFastRegister.setErrCode(wxOpenResult.getErrcode());
        wxFastRegister.setErrorMsg(wxOpenResult.getErrmsg());
        wxFastRegisterDao.addWxFastRegister(wxFastRegister);
        AddLicebseResponse response=new AddLicebseResponse();
        response.setErrCode(wxFastRegister.getErrCode());
        response.setErrMsg(wxFastRegister.getErrorMsg());
        return response;
    }
}
