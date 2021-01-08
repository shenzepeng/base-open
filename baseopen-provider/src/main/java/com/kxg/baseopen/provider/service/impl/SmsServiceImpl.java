package com.kxg.baseopen.provider.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.common.ReturnCode;
import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.dao.SmsDao;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.pojo.SmsInfo;
import com.kxg.baseopen.provider.service.SmsService;
import com.kxg.baseopen.provider.utils.JsonUtils;
import com.kxg.baseopen.provider.web.request.SentSmsRequest;
import com.kxg.baseopen.provider.web.request.VerifyRequest;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import java.util.List;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {
    //此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static final String accessKeyId = "LTAIksUCwdillc5m";
    private static final String accessKeySecret = "2x7FsOKzLryhw0YuA5w73EdEy8FJEb ";   // TODO 修改成自己的
    @Autowired
    private SmsDao smsDao;

    @Override
    public SzpJsonResult<IntegerResult> sent(SentSmsRequest sentSmsRequest){
        try {
            Integer newCode = (int)(Math.random()*9999)+100;
            SmsInfo kSmsInfo=new SmsInfo();
            kSmsInfo.setCode(newCode.toString());
            kSmsInfo.setPhoneNumber(sentSmsRequest.getPhoneNumber());
            SendSmsResponse sendSmsResponse = sendSms(sentSmsRequest.getPhoneNumber(), newCode.toString());
            kSmsInfo.setFlowId(sendSmsResponse.getRequestId());
            kSmsInfo.setFlowCode(sendSmsResponse.getCode());
            kSmsInfo.setFlowMsg(sendSmsResponse.getMessage());
            smsDao.addSms(kSmsInfo);
            log.info("sendSmsResponse {}", JsonUtils.convertObjectToJSON(sendSmsResponse));
        } catch (ClientException e) {
            log.error("sent sms error {}",e.toString());
        }
        IntegerResult integerResult=new IntegerResult();
        return SzpJsonResult.ok(integerResult);
    }

    @Override
    public SzpJsonResult<IntegerResult> verify(VerifyRequest request){
        List<SmsInfo> smsInfo = smsDao.findSmsInfo(request.getPhoneNumber());
        //没有收到验证码
        if (CollectionUtils.isEmpty(smsInfo)){
            throw new KxgException("99999","验证码不能为空");
        }
        //验证码已经验证
        SmsInfo kSmsInfo = smsInfo.get(0);
        if (kSmsInfo.getVetifyTimes()!=0){
            throw new KxgException("99999","验证码已经验证");
        }
        kSmsInfo.setVetifyTimes(1);
        smsDao.update(kSmsInfo);
        //验证码不正确
        if (!kSmsInfo.getCode().equals(request.getCode())){
            throw new KxgException("99999","验证码不正确");
        }
        IntegerResult integerResult=new IntegerResult();
        return SzpJsonResult.ok(integerResult);
    }

    private   SendSmsResponse sendSms(String telephone, String code) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        /**
         * //短信API产品名称（短信产品名固定，无需修改）
         */
        final String product = "Dysmsapi";
        /**
         * //短信API产品域名（接口地址固定，无需修改）
         */
        final String domain = "dysmsapi.aliyuncs.com";
        //替换成你的AK
        /**
         * //你的accessKeyId,参考本文档步骤2
         */
        final String accessKeyId = "LTAIksUCwdillc5m";
        /**
         * 你的accessKeySecret，参考本文档步骤2
         */
        final String accessKeySecret = "2x7FsOKzLryhw0YuA5w73EdEy8FJEb";
        /**
         * 初始化ascClient,暂时不支持多region（请勿修改）
         */
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(telephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("看好网络科技");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_160520239");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        /**
         * 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
         */
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }
}
