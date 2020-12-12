package com.kxg.baseopen.provider.service.impl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.gson.JsonParser;
import com.kxg.baseopen.provider.common.WechatOpenProperties;
import com.kxg.baseopen.provider.dao.AuthorAccessTokenDao;
import com.kxg.baseopen.provider.dao.AuthorizerInfoDao;
import com.kxg.baseopen.provider.dao.CallBackCodeDao;
import com.kxg.baseopen.provider.dto.PreAuthCodeDto;
import com.kxg.baseopen.provider.dto.wxauthor.JsonsRootBean;
import com.kxg.baseopen.provider.pojo.AuthorizerInfo;
import com.kxg.baseopen.provider.pojo.AuthorizerToken;
import com.kxg.baseopen.provider.pojo.CallBackCode;
import com.kxg.baseopen.provider.pojo.FastRegister;
import com.kxg.baseopen.provider.service.CreateSmallApplicationService;
import com.kxg.baseopen.provider.service.FastRegisterService;
import com.kxg.baseopen.provider.service.OpenWxService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import com.kxg.baseopen.provider.web.request.FastRegisterRequest;
import com.kxg.baseopen.provider.web.request.FastRegisterSearchRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
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
public class CreateSmallApplicationServiceImpl implements CreateSmallApplicationService {
    private static final JsonParser JSON_PARSER = new JsonParser();

    @Autowired
    private CallBackCodeDao callBackCodeDao;
    @Autowired
    private AuthorizerInfoDao authorizerInfoDao;
    @Autowired
    private AuthorAccessTokenDao authorAccessTokenDao;
    /**
     * 微信回调，获取code的接口url
     */
    private static final String WX_CALL_BACK_URL="http://shenzepengzuishuai.cn/baseopen-provider-1.0.0/create/sa/call/back/code";
    private static final String REDIRECT_URL="http://shenzepengzuishuai.cn/baseopen-provider-1.0.0/create/sa/redirect";
    @Autowired
    private OpenWxService openWxService;
    @Autowired
    private FastRegisterService fastRegisterService;

    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 第三方平台快速创建小程序.
     * 注意：创建任务逻辑串行，单次任务结束后才可以使用相同信息下发第二次任务，请注意规避任务阻塞
     *
     * @return .
     * @throws WxErrorException .
     * @ name               企业名（需与工商部门登记信息一致）
     * @ code               企业代码
     * @ codeType           企业代码类型 1：统一社会信用代码（18位） 2：组织机构代码（9位xxxxxxxx-x） 3：营业执照注册号(15位)
     * @ legalPersonaWechat 法人微信号
     * @ legalPersonaName   法人姓名（绑定银行卡）
     * @ componentPhone     第三方联系电话（方便法人与第三方联系）
     */
    @Override
    public WxOpenResult fastRegisterWeapp(FastRegisterRequest registerRequest) {
        if (StringUtils.isEmpty(registerRequest.getCode()) ||
                null == registerRequest.getCodeType() ||
                StringUtils.isEmpty(registerRequest.getComponentPhone()) ||
                StringUtils.isEmpty(registerRequest.getLegalPersonaName()) ||
                StringUtils.isEmpty(registerRequest.getName()) ||
                StringUtils.isEmpty(registerRequest.getLegalPersonaWechat())) {
            throw new RuntimeException("请检查参数");
        }
        //企业快速认证id
        Long companyRegisterID =null;
        //对企业三证和一码加锁，防止多次点击
        synchronized (registerRequest.getCode().intern()){
            List<FastRegister> register = fastRegisterService.findFastRegister(registerRequest.getCode());
            if (CollectionUtils.isEmpty(register)){
                //将企业信息保存到本地数据库
                FastRegister fastRegister = new FastRegister();
                fastRegister.setCode(registerRequest.getCode());
                fastRegister.setCodeType(registerRequest.getCodeType());
                fastRegister.setStatus(0);
                fastRegister.setComponentPhone(registerRequest.getComponentPhone());

                fastRegister.setLegalPersonaWechat(registerRequest.getLegalPersonaWechat());
                fastRegister.setLegalPersonaName(registerRequest.getLegalPersonaName());
                fastRegister.setName(registerRequest.getName());
                companyRegisterID = fastRegister.getId();
            }else {
                companyRegisterID =register.get(0).getId();
            }
        }
        //请求微信微信
        Map<String, Object> map = new HashMap<>();
        map.put("name", registerRequest.getName());
        map.put("code", registerRequest.getCode());
        map.put("code_type", registerRequest.getCodeType());
        map.put("legal_persona_wechat", registerRequest.getLegalPersonaWechat());
        map.put("legal_persona_name", registerRequest.getLegalPersonaName());
        map.put("component_phone", registerRequest.getComponentPhone());
        String targetUrl = FAST_REGISTER_WEAPP_URL + "&component_access_token=" + openWxService.getAccessToken();
        log.info("FAST_REGISTER_WEAPP_URL targetUrl {}",targetUrl);
        String response = postInfo(targetUrl, map);
        WxOpenResult wxOpenResult = WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
        FastRegister upFasterRegister = new FastRegister();
        upFasterRegister.setErrCode(wxOpenResult.getErrcode());
        upFasterRegister.setErrorMsg(wxOpenResult.getErrmsg());
        upFasterRegister.setId(companyRegisterID);
        fastRegisterService.updateFastRegister(upFasterRegister);
        return wxOpenResult;
    }

    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 查询第三方平台快速创建小程序的任务状态
     * 注意：该接口只提供当下任务结果查询，不建议过分依赖该接口查询所创建小程序。
     * 小程序的成功状态可在第三方服务器中自行对账、查询。
     * 不要频繁调用search接口，消息接收需通过服务器查看。调用search接口会消耗接口整体调用quato
     *
     * @throws WxErrorException .
     * @ name               企业名（需与工商部门登记信息一致）
     * @ legalPersonaWechat 法人微信号
     * @ legalPersonaName   法人姓名（绑定银行卡）
     */
    @Override
    public WxOpenResult fastRegisterWeappSearch(FastRegisterSearchRequest registerSearchRequest) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", registerSearchRequest.getName());
        map.put("legal_persona_wechat", registerSearchRequest.getLegalPersonaWechat());
        map.put("legal_persona_name", registerSearchRequest.getLegalPersonaName());
        String targetUrl = FAST_REGISTER_WEAPP_SEARCH_URL + "&component_access_token=" + openWxService.getAccessToken();
        String response = postInfo(targetUrl, map);
        return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);

    }


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

        StringBuilder targetUrl=new StringBuilder();
        if ("0".equals(useQrCode)){
            targetUrl.append(MAKE_SURE_URL);
            targetUrl.append("&component_appid="+WechatOpenProperties.componentAppId);
            //预生成码
            targetUrl.append("&pre_auth_code="+getPreAuthCode());
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
            targetUrl.append("&pre_auth_code="+getPreAuthCode());
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
        String targetURl=GET_APP_REFRESH_CODE+openWxService.getAccessToken();
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid",WechatOpenProperties.componentAppId);
        map.put("authorization_code",code);
        String postInfo = postInfo(targetURl, map);
        JsonsRootBean jsonsRootBean = JsonUtils.toBean(postInfo, JsonsRootBean.class);
        //保存权限key
        saveAppRefreshToken(jsonsRootBean,postInfo);
        //保存第一个的调用凭证
        saveFirstAccessToken(jsonsRootBean);
        return "授权成功";
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

    /**
     * 小程序的初次accessToken
     * @param jsonsRootBean
     */
    private void saveFirstAccessToken(JsonsRootBean jsonsRootBean){
        AuthorizerToken authorizerToken=new AuthorizerToken();
        authorizerToken.setAuthorizerAccessToken(jsonsRootBean.getAuthorizationInfo().getAuthorizerAccessToken());
        authorizerToken.setAuthorizerAppid(jsonsRootBean.getAuthorizationInfo().getAuthorizerAppid());
        Long expiredTime=System.currentTimeMillis()+60 * 1000 * 110;
        authorizerToken.setExpiredTime(expiredTime.toString());
        authorAccessTokenDao.addAuthorToken(authorizerToken);
    }
    private void saveCode(String code, String time){
        CallBackCode callBackCode=new CallBackCode();
        callBackCode.setAuthorizationCode(code);
        callBackCode.setExpiredTime(time);
        callBackCodeDao.addCallBackCode(callBackCode);
    }

    /**
     * 获取预售权码
     * @return
     */
    public String getPreAuthCode() {
        Map<String,Object> map=new HashMap<>();
        map.put("component_appid", WechatOpenProperties.componentAppId);
        String targetUrl=PRE_AUTH_CODE+ "?component_access_token=" + openWxService.getAccessToken();
        String postInfo = postInfo(targetUrl, map);
        if(StringUtils.isEmpty(postInfo)){
            throw new RuntimeException("获取预授权码失败");
        }
        PreAuthCodeDto preAuthCodeDto = JsonUtils.toBean(postInfo, PreAuthCodeDto.class);
        return preAuthCodeDto.getPre_auth_code();
    }

    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        String accessToken = openWxService.getAccessToken();
        HashMap<String, String> accessTokenMap = new HashMap<>();
        accessTokenMap.put("component_access_token", accessToken);
        return HttpClientUtil.postJson(targetUrl, bodyMsg, accessTokenMap);
    }


}
