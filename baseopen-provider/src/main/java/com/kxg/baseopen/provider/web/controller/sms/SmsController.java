package com.kxg.baseopen.provider.web.controller.sms;


import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.service.SmsService;
import com.kxg.baseopen.provider.web.request.SentSmsRequest;
import com.kxg.baseopen.provider.web.request.VerifyRequest;
import com.kxg.baseopen.provider.web.response.IntegerResult;
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
    @Autowired
    private SmsService smsService;
    @PostMapping("sent")
    public KxgResponse<IntegerResult> sentSms(@RequestBody SentSmsRequest request){
        return smsService.sent(request);
    }

    @PostMapping("verify")
    public KxgResponse<IntegerResult> verifySms(@RequestBody VerifyRequest request){
        return smsService.verify(request);
    }
}
