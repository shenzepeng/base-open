package com.kxg.baseopen.provider.config;

import lombok.Data;

/**
 * 要写注释呀
 */

public class TenpayConfig {
    //app的在微信支付下的appid
    public static String APP_ID="wxbe88495f1fe5297e";
    //app在微信支付下appid的密钥
    public static String APP_ID_SECRET="692ce38aa3764fbbf3edc1b3a457f737";
    //商户号
    public static String MCH_ID="1560959121";
    //商户的key(API密匙)
    public static String MCH_KEY="2354095c5b08436cae8d81ed32d44979";
    //API支付请求地址
    public static String PAY_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    //API查询请求地址
    public static String QUERY_URL="https://api.mch.weixin.qq.com/pay/orderquery";
    //Sign=WXPay
    public static String PACKAGE_VALUE="Sign=WXPay";
    //微信支付回调
    public static String WX_PAY_NOTIFY_URL="http://www.shenzepengzuishuai.cn/baseopen-provider-1.0.0/notify";
}
