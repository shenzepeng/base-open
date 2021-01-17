package com.kxg.baseopen.provider.config;

import com.kxg.baseopen.provider.utils.DownLoadFileUtils;
import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 要写注释呀
 */
@Slf4j
@Component
public class PayConfig {

    private static final String P12_URL="https://kxg-neituibao-jianli.oss-cn-beijing.aliyuncs.com/sss/1609336977085.p12";
    private static final String P12_PATH="/Users/mac/Desktop/p12";
    private static final String P12_FILE_NAME="A_B_C_D_E.p12";
    //初始化配置
    @SneakyThrows
    @PostConstruct
    public void initP12(){
        File filePath=new File(P12_PATH);
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        DownLoadFileUtils.downLoadFromUrl(P12_URL,P12_FILE_NAME,P12_PATH);
    }
    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(TenpayConfig.APP_ID);
        wxPayConfig.setAppSecret(TenpayConfig.APP_ID_SECRET);
        wxPayConfig.setMchId(TenpayConfig.MCH_ID);
        wxPayConfig.setMchKey(TenpayConfig.MCH_KEY);
        wxPayConfig.setAppAppId(TenpayConfig.APP_ID);
        wxPayConfig.setNotifyUrl(TenpayConfig.WX_PAY_NOTIFY_URL);
        wxPayConfig.setKeyPath(P12_PATH+"/"+P12_FILE_NAME);
        wxPayConfig.setNotifyUrl("accountConfig.getNotifyUrl()");
        return wxPayConfig;
    }
    @Bean
    public BestPayServiceImpl bestPayService(WxPayConfig wxPayConfig) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        return bestPayService;
    }




}
