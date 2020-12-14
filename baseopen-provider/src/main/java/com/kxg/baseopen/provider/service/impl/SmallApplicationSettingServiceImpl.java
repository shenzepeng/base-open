package com.kxg.baseopen.provider.service.impl;
import com.kxg.baseopen.provider.dto.request.SetWxUrlRequest;
import com.kxg.baseopen.provider.dto.request.SettingWxNameRequest;
import com.kxg.baseopen.provider.dto.response.SetWxUrlResponse;
import com.kxg.baseopen.provider.dto.response.SettingWxNameResponse;
import com.kxg.baseopen.provider.dto.response.UpdateWxServerAddressResponse;
import cn.binarywang.wx.miniapp.bean.WxMaDomainAction;
import com.kxg.baseopen.provider.dto.request.UpdateWxServerAddressRequest;
import com.kxg.baseopen.provider.service.CreateSmallApplicationService;
import com.kxg.baseopen.provider.service.SmallApplicationSettingService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  小程序设置
 */
@Slf4j
@Service
public class SmallApplicationSettingServiceImpl implements SmallApplicationSettingService {
    @Autowired
    private CreateSmallApplicationService createSmallApplicationService;

    /**  设置请求服务器的url
     *@  小程序的appId
     * @  域名操作参数
     *
     * @return
     * @throws WxErrorException
     */
    @Override
    public UpdateWxServerAddressResponse updateWxServiceAddress(UpdateWxServerAddressRequest updateWxServerAddressRequest) throws WxErrorException {
        String targetUrl=MODIFY_DOMAIN_URL+createSmallApplicationService.getLastAppLastAccessToken(updateWxServerAddressRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("action",updateWxServerAddressRequest.getAction().getAction());
        map.put("requestdomain",updateWxServerAddressRequest.getRequestDomain());
        map.put("wsrequestdomain",updateWxServerAddressRequest.getWsRequestDomain());
        map.put("uploaddomain",updateWxServerAddressRequest.getUploadDomain());
        map.put("downloaddomain",updateWxServerAddressRequest.getDownloadDomain());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo, UpdateWxServerAddressResponse.class);
    }

    /**
     * 设置业务域名
     * @param request
     * @return
     */
    @Override
    public SetWxUrlResponse setWebViewDomain(SetWxUrlRequest request) {
        String targetUrl=SET_WEB_VIEW_DOMAIN_URL+createSmallApplicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("action",request.getAction().getAction());
        map.put("webviewdomain",request.getWebviewDomain());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,SetWxUrlResponse.class);
    }

    @Override
    public void bindTester(String wechatId) throws WxErrorException {

    }

    @Override
    public void unbindTester(String wechatId) throws WxErrorException {

    }

    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg,null);
    }

}
