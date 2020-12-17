package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.dao.AuthorAccessTokenDao;
import com.kxg.baseopen.provider.dao.AuthorizerInfoDao;
import com.kxg.baseopen.provider.dao.OpenWxAccessTokenDao;
import com.kxg.baseopen.provider.dto.getappaccesstoken.AppAccessToken;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.pojo.AuthorizerInfo;
import com.kxg.baseopen.provider.pojo.AuthorizerToken;
import com.kxg.baseopen.provider.pojo.OpenWxAccessToken;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private AuthorAccessTokenDao authorAccessTokenDao;
    @Autowired
    private AuthorizerInfoDao authorizerInfoDao;
    @Autowired
    private OpenWxAccessTokenDao openWxAccessTokenDao;
    @Override
    public String getComponentAccessToken() {
        List<OpenWxAccessToken> lastAccessToken = openWxAccessTokenDao.getLastAccessToken();
        if (CollectionUtils.isEmpty(lastAccessToken)) {
            String componentAccessToken = getComponentAccessToken();
            log.info("componentAccessToken {}",componentAccessToken);
            addNewAccessToken(componentAccessToken);
            return componentAccessToken;
        }
        OpenWxAccessToken openWxAccessToken = lastAccessToken.get(0);
        if (Long.parseLong(openWxAccessToken.getExpirationTime())<System.currentTimeMillis()){
            String componentAccessToken = getComponentAccessToken();
            log.info("componentAccessToken {}",componentAccessToken);
            addNewAccessToken(componentAccessToken);
        }
        return openWxAccessToken.getAccessToken();
    }

    private void  addNewAccessToken(String accessToken){
        OpenWxAccessToken openWxAccessToken=new OpenWxAccessToken();
        openWxAccessToken.setAccessToken(accessToken);
        Long expirationTime = System.currentTimeMillis() + 60 * 1000 * 100;
        openWxAccessToken.setExpirationTime(expirationTime.toString());
        openWxAccessTokenDao.addAccessToken(openWxAccessToken);
    }

    @Override
    public String getSmallAppLastAccessToken(String appId) {
        List<AuthorizerToken> lastAuthorToken = authorAccessTokenDao.findLastAuthorToken(appId);
        List<AuthorizerInfo> lastAppIdInfo = authorizerInfoDao.findLastAppIdInfo(appId);
        if (CollectionUtils.isEmpty(lastAppIdInfo)){
            throw new RuntimeException("该商户未授权");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid", WechatOpenProperties.componentAppId);
        map.put("authorizer_appid",lastAppIdInfo.get(0).getAuthorizerAppid());
        map.put("authorizer_refresh_token",lastAppIdInfo.get(0).getAuthorizerRefreshToken());
        String targetUrl=GET_APP_LAST_ACCESS_TOKEN+"?component_access_token="+ getComponentAccessToken();
        if (CollectionUtils.isEmpty(lastAuthorToken)||Long.parseLong(lastAuthorToken.get(0).getExpiredTime())<System.currentTimeMillis()){
            String postInfo = postInfo(targetUrl,null,null);
            AppAccessToken appAccessToken = JsonUtils.toBean(postInfo, AppAccessToken.class);
            save(appAccessToken,appId);
            return appAccessToken.getAuthorizerAccessToken();
        }
        return lastAuthorToken.get(0).getAuthorizerAccessToken();
    }
    /**
     * 获取小程序最新的
     * @param appAccessToken
     * @param appId
     */
    private void save(AppAccessToken appAccessToken,String appId){
        AuthorizerToken authorizerToken=new AuthorizerToken();
        Long expirationTime = System.currentTimeMillis() + 30 * 1000 * 110;
        authorizerToken.setExpiredTime(expirationTime.toString());
        authorizerToken.setAuthorizerAppid(authorizerToken.getAuthorizerAppid());
        authorizerToken.setAuthorizerAccessToken(appAccessToken.getAuthorizerAccessToken());
        authorAccessTokenDao.addAuthorToken(authorizerToken);
        AuthorizerInfo authorizerInfo=new AuthorizerInfo();
        authorizerInfo.setAuthorizerAppid(appId);
        authorizerInfo.setAuthorizerRefreshToken(appAccessToken.getAuthorizerAccessToken());
        authorizerInfoDao.add(authorizerInfo);
    }


    private String postInfo(String targetUrl, Map<String, Object> bodyMsg,  HashMap<String, String> header) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, header);
    }

}
