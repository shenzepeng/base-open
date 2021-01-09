package com.kxg.baseopen.provider.web.controller.sms;


import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.service.SmsService;
import com.kxg.baseopen.provider.service.UserService;
import com.kxg.baseopen.provider.web.request.SentSmsRequest;
import com.kxg.baseopen.provider.web.request.VerifyRequest;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import com.kxg.baseopen.provider.web.response.SmsLoginResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("sms")
public class SmsController {
    //用户添加servic
    @Autowired
    private UserService userService;
    @Autowired
    private SmsService smsService;
    @ApiOperation("发送短信")
    @PostMapping("sent")
    public SzpJsonResult<IntegerResult> sentSms(@RequestBody SentSmsRequest request){

        return smsService.sent(request);
    }

    /**
     * 短信验证码登录
     * 如果系统中没有手机号添加到新的
     * 系统中有手机号则不作操作
     * @param request
     * @return
     */
    @ApiOperation("验证短信")
    @PostMapping("verify")
    public SzpJsonResult<IntegerResult> verifySms(@RequestBody VerifyRequest request){
        if (StringUtils.isEmpty(request.getPhoneNumber())){
            throw new KxgException("99999","手机号不能为空");
        }
        if (StringUtils.isEmpty(request.getCode())){
            throw new KxgException("99999","验证码不能为空");
        }
        return smsService.verify(request);
    }

    @ApiOperation("验证码登录")
    @PostMapping("smsLogin")
    public SzpJsonResult<SmsLoginResponse> smsLogin(@RequestBody VerifyRequest request){
        if (StringUtils.isEmpty(request.getPhoneNumber())){
            throw new KxgException("99999","手机号不能为空");
        }
        if (StringUtils.isEmpty(request.getCode())){
            throw new KxgException("99999","验证码不能为空");
        }
        //看是否有这个人，没有的话，添加新的
        return SzpJsonResult.ok(userService.login(request.getPhoneNumber()));
    }
}
