package com.kxg.baseopen.provider.openwx.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.dao.AuthorAccessTokenDao;
import com.kxg.baseopen.provider.dao.AuthorizerInfoDao;
import com.kxg.baseopen.provider.dao.CallBackCodeDao;
import com.kxg.baseopen.provider.dto.PreAuthCodeDto;
import com.kxg.baseopen.provider.dto.getappaccesstoken.AppAccessToken;
import com.kxg.baseopen.provider.dto.wxauthor.JsonsRootBean;
import com.kxg.baseopen.provider.openwx.AuthorizationService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.pojo.AuthorizerInfo;
import com.kxg.baseopen.provider.pojo.AuthorizerToken;
import com.kxg.baseopen.provider.pojo.CallBackCode;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 要写注释呀
 */

@Slf4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @NacosValue(value = "${WX_CALL_BACK_URL}",autoRefreshed = true)
    private String WX_CALL_BACK_URL;
    @Autowired
    private CallBackCodeDao callBackCodeDao;
    @Autowired
    private AuthorAccessTokenDao authorAccessTokenDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthorizerInfoDao authorizerInfoDao;
    /**
     * 生成客户需要的链接
     * @param appId
     * @return
     */
    @SneakyThrows
    @Override
    public String getCustomerMakeSureUrl(String appId,String useQrCode) {
//         String   redirectUrl  =   java.net.URLEncoder.encode(WX_CALL_BACK_URL,"utf-8");
        String   redirectUrl  =   WX_CALL_BACK_URL;
        String preAuthCode = getPreAuthCode();
        log.info("preAuthCode {}",preAuthCode);
        StringBuilder targetUrl=new StringBuilder();
        if ("0".equals(useQrCode)){
            targetUrl.append(MAKE_SURE_URL);
            targetUrl.append("&component_appid="+ WechatOpenProperties.componentAppId);
            //预生成码
            targetUrl.append("&pre_auth_code="+preAuthCode);
            //回调，获取微信code的接口
            targetUrl.append("&redirect_uri="+redirectUrl);
            /**
             * 要授权的帐号类型：1 则商户点击链接后，手机端仅展示公众号、
             * 2 表示仅展示小程序，3 表示公众号和小程序都展示。
             * 如果为未指定，则默认小程序和公众号都展示。
             * 第三方平台开发者可以使用本字段来控制授权的帐号类型。
             */
            targetUrl.append("&auth_type=2");
            //限制能绑定的appid
            if (!StringUtils.isEmpty(appId)) {
                targetUrl.append("&biz_appid=" + appId);
            }
            targetUrl.append("#wechat_redirect");
        }else {
            targetUrl.append(SCAN_QR_CODE_URL);
            targetUrl.append("?component_appid="+WechatOpenProperties.componentAppId);
            targetUrl.append("&pre_auth_code="+preAuthCode);
            targetUrl.append("&redirect_uri="+redirectUrl);
            //限制能绑定的appid
            if (!StringUtils.isEmpty(appId)) {
                targetUrl.append("&biz_appid=" + appId);
            }
            targetUrl.append("&auth_type=2");
        }
        StringBuilder sb=new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        sb.append("<div text-align=center>");
        sb.append("<h3 text-align=center>");
        sb.append("<a href=\""+targetUrl.toString()+" \">");
        sb.append("点我进行授权登录");
        sb.append("</a >");
        sb.append("</h3>");
        sb.append("</div>");
        sb.append("</html>");
        sb.append("</body>");
        log.info("getCustomerMakeSureUrl {}",sb.toString());
        return sb.toString();
    }

    /**
     * 微信回调的code
     * 获取app的授权信息
     * @param code
     * @param time
     * @return
     */
    @Override
    public String getCallBackUrl(String code, Integer time) {
        //保存code到数据库
        saveCode(code,time.toString());
        //获取小程序的权限
        String targetURl=GET_APP_REFRESH_CODE+"?component_access_token="+tokenService.getComponentAccessToken();
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid",WechatOpenProperties.componentAppId);
        map.put("authorization_code",code);
        String postInfo = postInfo(targetURl, map,null);
        JsonsRootBean jsonsRootBean = JsonUtils.toBean(postInfo, JsonsRootBean.class);
        //保存权限key
        saveAppRefreshToken(jsonsRootBean,postInfo);
        //保存第一个的调用凭证
        saveFirstAccessToken(jsonsRootBean);
        return "授权成功";
    }

    private void saveCode(String code, String time){
        CallBackCode callBackCode=new CallBackCode();
        callBackCode.setAuthorizationCode(code);
        callBackCode.setExpiredTime(time);
        callBackCodeDao.addCallBackCode(callBackCode);
    }
    /**
     * 小程序的初次accessToken
     * @param jsonsRootBean
     */
    private void saveFirstAccessToken(JsonsRootBean jsonsRootBean){
        AuthorizerToken authorizerToken=new AuthorizerToken();
        authorizerToken.setAuthorizerAccessToken(jsonsRootBean.getAuthorizationInfo().getAuthorizerAccessToken());
        authorizerToken.setAuthorizerAppid(jsonsRootBean.getAuthorizationInfo().getAuthorizerAppid());
        Long expiredTime=System.currentTimeMillis()+60 * 1000 * 100;
        authorizerToken.setExpiredTime(expiredTime.toString());
        authorAccessTokenDao.addAuthorToken(authorizerToken);
    }

    /**
     * 获取预售权码
     * @return
     */
    public String getPreAuthCode() {
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid", WechatOpenProperties.componentAppId);
        String targetUrl=PRE_AUTH_CODE+ "?component_access_token=" +tokenService.getComponentAccessToken();
        String postInfo = postInfo(targetUrl, map,null);
        if(StringUtils.isEmpty(postInfo)){
            throw new RuntimeException("获取预授权码失败");
        }
        PreAuthCodeDto preAuthCodeDto = JsonUtils.toBean(postInfo, PreAuthCodeDto.class);
        return preAuthCodeDto.getPreAuthCode();
    }

    /**
     * 获取小程序最新的
     * @param appAccessToken
     * @param appId
     */
    private void save(AppAccessToken appAccessToken, String appId){
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

    /**
     * 保存小程序的refreshtoken 以及相关权限信息
     * @param jsonsRootBean
     * @param postInfo
     */
    private void  saveAppRefreshToken(JsonsRootBean jsonsRootBean,String postInfo){
        AuthorizerInfo authorizerInfo=new AuthorizerInfo();
        authorizerInfo.setAuthorizerAppid(jsonsRootBean.getAuthorizationInfo().getAuthorizerAppid());
        authorizerInfo.setAuthorizerRefreshToken(jsonsRootBean.getAuthorizationInfo().getAuthorizerRefreshToken());
        authorizerInfo.setFuncInfo(postInfo);
        authorizerInfoDao.add(authorizerInfo);
    }
    private String postInfo(String targetUrl, Map<String, Object> bodyMsg,  HashMap<String, String> header) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, header);
    }
}
