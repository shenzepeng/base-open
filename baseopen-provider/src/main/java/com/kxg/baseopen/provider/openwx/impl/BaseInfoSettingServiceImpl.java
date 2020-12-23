package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.dto.CheckWxNameDto;
import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import com.kxg.baseopen.provider.enums.WxActionEnums;
import com.kxg.baseopen.provider.openwx.BaseInfoSettingService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 要写注释呀
 */
@Service
public class BaseInfoSettingServiceImpl implements BaseInfoSettingService {
    @Autowired
    private TokenService tokenService;
    /**
     * 设置小程序的名称
     * @param request
     * @return
     */
    @Override
    public SettingWxNameResponse setAppName(SettingWxNameRequest request) {
        //获取小程序最新的token
        String lastAppLastAccessToken = tokenService.getSmallAppLastAccessToken(request.getAppId());
        String targetUrl=SET_APP_NAME+"?access_token="+lastAppLastAccessToken;
        Map<String,Object> map=new HashMap<>();
        map.put("nick_name",request.getNickName());
        if (CollectionUtils.isEmpty(request.getNamingOtherStuff())){
            for (int i = 0; i < request.getNamingOtherStuff().size(); i++) {
                map.put("naming_other_stuff_"+i,request.getNamingOtherStuff().get(i));
            }
        }
        String postInfo = postInfo(targetUrl, map,null);
        return JsonUtils.toBean(postInfo,SettingWxNameResponse.class);
    }

    /**
     * 检查小程序的名称
     * @param appId
     * @param name
     * @return
     */
    @Override
    public CheckWxNameDto checkAppName(String appId, String name) {
        //获取小程序最新的token
        String lastAppLastAccessToken = tokenService.getSmallAppLastAccessToken(appId);
        String targetUrl=CHECK_APP_NAME+"?access_token="+lastAppLastAccessToken;
        Map<String,Object> map=new HashMap<>();
        map.put("nick_name",name);
        String postInfo = postInfo(targetUrl, map,null);
        return  JsonUtils.toBean(postInfo,CheckWxNameDto.class);
    }

    @Override
    public UpdateWxServerAddressResponse updateWxServiceAddress(UpdateWxServerAddressRequest updateWxServerAddressRequest) {
        String targetUrl=MODIFY_DOMAIN_URL+"?access_token="+tokenService.getSmallAppLastAccessToken(updateWxServerAddressRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("action",WxActionEnums.formatByCode(updateWxServerAddressRequest.getAction()).getName());
        map.put("requestdomain",updateWxServerAddressRequest.getRequestDomain());
        map.put("wsrequestdomain",updateWxServerAddressRequest.getWsRequestDomain());
        map.put("uploaddomain",updateWxServerAddressRequest.getUploadDomain());
        map.put("downloaddomain",updateWxServerAddressRequest.getDownloadDomain());
        String postInfo = postInfo(targetUrl, map,null);
        return JsonUtils.toBean(postInfo, UpdateWxServerAddressResponse.class);
    }

    @Override
    public SetWxUrlResponse setWebViewDomain(SetWxUrlRequest request)  {
        String targetUrl=SET_WEB_VIEW_DOMAIN_URL+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("action", WxActionEnums.formatByCode(request.getAction()).getName());
        map.put("webviewdomain",request.getWebviewDomain());
        String postInfo = postInfo(targetUrl, map,null);
        return JsonUtils.toBean(postInfo,SetWxUrlResponse.class);
    }


    /**
     * 绑定小程序体验者
     *
     * @ wechatId 体验者微信号（不是openid）
     * @return wx open ma bind tester result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public BindTesterResponse bindTester(BindTesterRequest request){
        String targetUrl=API_BIND_TESTER+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("wechatid",request.getWeChatId());
        String postInfo = postInfo(targetUrl, paramJson,null);
        return JsonUtils.toBean(postInfo, BindTesterResponse.class);
    }

    /**
     * 解除绑定小程序体验者
     *
     *  wechatId 体验者微信号（不是openid）
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public UnBindTesterResponse unbindTester(UnBindTesterRequest request) {
        String targetUrl=API_UNBIND_TESTER+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("wechatid",request.getAppId());
        String postInfo = postInfo(targetUrl, paramJson,null);
        return JsonUtils.toBean(postInfo,UnBindTesterResponse.class);
    }

    /**
     * 设置小程序隐私设置（是否可被搜索）
     *
     * @ status 1表示不可搜索，0表示可搜索
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public ChangeWxaSearchStatusResponse changeWxaSearchStatus(ChangeWxaSearchStatusRequest request) {
        String targetUrl=API_CHANGE_WXA_SEARCH_STATUS+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("status",request.getStatus());
        String postInfo = postInfo(targetUrl, paramJson,null);
        return   JsonUtils.toBean(postInfo,ChangeWxaSearchStatusResponse.class);
    }

    /**
     * 2. 查询小程序当前隐私设置（是否可被搜索）
     *
     * @return the wxa search status
     * @throws WxErrorException the wx error exception
     */
    @Override
    public WxOpenMaSearchStatusResponse getWxaSearchStatus(WxOpenMaSearchStatusRequest request) {
        String targetURl=API_GET_WXA_SEARCH_STATUS+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String info = getInfo(targetURl, null);
        return JsonUtils.toBean(info,WxOpenMaSearchStatusResponse.class);
    }

    @Override
    public FixHeaderImgResponse fixHeader(FixHeaderImgRequest fixHeaderImgRequest) {
        String targetUrl=CHANGE_IMG_HEADER+"?access_token="+tokenService.getSmallAppLastAccessToken(fixHeaderImgRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("head_img_media_id",fixHeaderImgRequest.getHeadImgMediaId());
        map.put("x1",fixHeaderImgRequest.getX1());
        map.put("y1",fixHeaderImgRequest.getY1());
        map.put("x2",fixHeaderImgRequest.getX2());
        map.put("y2",fixHeaderImgRequest.getY2());
        String postInfo = postInfo(targetUrl, map, null);
        return JsonUtils.toBean(postInfo,FixHeaderImgResponse.class);
    }

    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }


    private String postInfo(String targetUrl, Map<String, Object> bodyMsg,  HashMap<String, String> header) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, header);
    }
}
