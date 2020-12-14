package com.kxg.baseopen.provider.service.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import com.kxg.baseopen.provider.enums.WxActionEnums;
import com.kxg.baseopen.provider.service.CreateSmallApplicationService;
import com.kxg.baseopen.provider.service.WxAppOpenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.ma.WxMaOpenCommitExtInfo;
import me.chanjar.weixin.open.bean.ma.WxMaQrcodeParam;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.executor.MaQrCodeRequestExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.chanjar.weixin.open.api.WxOpenMaService;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class WxAppOpenServiceImpl  implements WxAppOpenService {
    @Autowired
    private CreateSmallApplicationService applicationService;
    /**
     * 设置小程序服务器域名.
     *
     * <pre>
     *     授权给第三方的小程序，其服务器域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的服务器域名将被删除，
     *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加第三方自身的域名。
     *     提示：需要先将域名登记到第三方平台的小程序服务器域名中，才可以调用接口进行配置
     * </pre>
     */
    String API_MODIFY_DOMAIN = "https://api.weixin.qq.com/wxa/modify_domain";

    /**
     * 设置小程序业务域名（仅供第三方代小程序调用）
     * <pre>
     *     授权给第三方的小程序，其业务域名只可以为第三方的服务器，当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
     *     只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
     * 提示：
     * 1、需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
     * 2、为授权的小程序配置域名时支持配置子域名，例如第三方登记的业务域名如为qq.com，则可以直接将qq.com及其子域名（如xxx.qq.com）也配置到授权的小程序中。
     * </pre>
     */
    String API_SET_WEBVIEW_DOMAIN = "https://api.weixin.qq.com/wxa/setwebviewdomain";

//    /**
//     * 获取帐号基本信息
//     * <pre>
//     * GET请求
//     * 注意：需要使用1.3环节获取到的新创建小程序appid及authorization_code换取authorizer_refresh_token进而得到authorizer_access_token。
//     * </pre>
//     */
//    String API_GET_ACCOUNT_BASICINFO = "https://api.weixin.qq.com/cgi-bin/account/getaccountbasicinfo";

    /**
     * 绑定微信用户为小程序体验者
     */
    String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester";


    /**
     * 解除绑定微信用户为小程序体验者
     */
    String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester";


    /**
     * 获取体验者列表
     */
    String API_GET_TESTERLIST = "https://api.weixin.qq.com/wxa/memberauth";

    /**
     * 以下接口基础信息设置
     * <p>
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21517799059ZSEMr&token=6f965b5daf30a98a6bbd2a386faea5c934e929bf&lang=zh_CN
     * </p>
     * 1. 设置小程序隐私设置（是否可被搜索）
     */
    String API_CHANGE_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/changewxasearchstatus";

    /**
     * 2. 查询小程序当前隐私设置（是否可被搜索）
     */
    String API_GET_WXA_SEARCH_STATUS = "https://api.weixin.qq.com/wxa/getwxasearchstatus";

//    /**
//     * 3.1. 获取展示的公众号信息
//     */
//    String API_GET_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/getshowwxaitem";
//
//    /**
//     * 3.2 设置展示的公众号
//     */
//    String API_UPDATE_SHOW_WXA_ITEM = "https://api.weixin.qq.com/wxa/updateshowwxaitem";


    /**
     * 以下接口为三方平台代小程序实现的代码管理功能
     * <p>
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140610_Uavc4&token=fe774228c66725425675810097f9e48d0737a4bf&lang=zh_CN
     * </p>
     * 1. 为授权的小程序帐号上传小程序代码
     */
    String API_CODE_COMMIT = "https://api.weixin.qq.com/wxa/commit";

    /**
     * 2. 获取体验小程序的体验二维码
     */
    String API_TEST_QRCODE = "https://api.weixin.qq.com/wxa/get_qrcode";

    /**
     * 3. 获取授权小程序帐号的可选类目
     */
    String API_GET_CATEGORY = "https://api.weixin.qq.com/wxa/get_category";
    /**
     * 获取已经设置的类目
     */
    String API_GET_SETTED_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
    /**
     * 添加类目
     */
    String API_POST_SET_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
    /**
     * 删除类目
     */
    String API_POST_DELETE_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";

    /**
     * 4. 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
     */
    String API_GET_PAGE = "https://api.weixin.qq.com/wxa/get_page";

    /**
     * 5. 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
     */
    String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit";

    /**
     * 7. 查询某个指定版本的审核状态（仅供第三方代小程序调用）
     */
    String API_GET_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_auditstatus";

    /**
     * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
     */
    String API_GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";

    /**
     * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
     */
    String API_RELEASE = "https://api.weixin.qq.com/wxa/release";

    /**
     * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用)
     */
    String API_CHANGE_VISITSTATUS = "https://api.weixin.qq.com/wxa/change_visitstatus";

    /**
     * 11.小程序版本回退（仅供第三方代小程序调用）
     */
    String API_REVERT_CODE_RELEASE = "https://api.weixin.qq.com/wxa/revertcoderelease";

    /**
     * 12.查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
     */
    String API_GET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/getweappsupportversion";

    /**
     * 13.设置最低基础库版本（仅供第三方代小程序调用）
     */
    String API_SET_WEAPP_SUPPORT_VERSION = "https://api.weixin.qq.com/cgi-bin/wxopen/setweappsupportversion";

    /**
     * 14.设置小程序“扫普通链接二维码打开小程序”能力
     * <p>
     * https://mp.weixin.qq.com/debug/wxadoc/introduction/qrcode.html
     * 14.1 增加或修改二维码规则
     */
    String API_QRCODE_JUMP_ADD = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpadd";

    /**
     * 14.2 获取已设置的二维码规则
     */
    String API_QRCODE_JUMP_GET = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpget";

    /**
     * 14.3 获取校验文件名称及内容
     */
    String API_QRCODE_JUMP_DOWNLOAD = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpdownload";

    /**
     * 14.4 删除已设置的二维码规则
     */
    String API_QRCODE_JUMP_DELETE = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumpdelete";

    /**
     * 14.5 发布已设置的二维码规则
     */
    String API_QRCODE_JUMP_PUBLISH = "https://api.weixin.qq.com/cgi-bin/wxopen/qrcodejumppublish";

    /**
     * 15.小程序审核撤回
     * <p>
     * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
     * </p>
     */
    String API_UNDO_CODE_AUDIT = "https://api.weixin.qq.com/wxa/undocodeaudit";

    /**
     * 16.1 小程序分阶段发布-分阶段发布接口
     */
    String API_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/grayrelease";

    /**
     * 16.2 小程序分阶段发布-取消分阶段发布
     */
    String API_REVERT_GRAY_RELEASE = "https://api.weixin.qq.com/wxa/revertgrayrelease";

    /**
     * 16.3 小程序分阶段发布-查询当前分阶段发布详情
     */
    String API_GET_GRAY_RELEASE_PLAN = "https://api.weixin.qq.com/wxa/getgrayreleaseplan";


    /**
     * 查询服务商的当月提审限额和加急次数（Quota）
     */
    String API_QUERY_QUOTA = "https://api.weixin.qq.com/wxa/queryquota";

    /**
     * 加急审核申请
     */
    String API_SPEED_AUDIT = "https://api.weixin.qq.com/wxa/speedupaudit";

    /**
     * 获得小程序的域名配置信息
     *
     * @return the domain
     * @throws WxErrorException the wx error exception
     */
    @Override
    public AppDomainResponse getDomain(AppDomainRequest request) throws WxErrorException{
        String lastAppLastAccessToken = applicationService.getLastAppLastAccessToken(request.getAppId());
        String targetUrl=API_MODIFY_DOMAIN+"?access_token="+lastAppLastAccessToken;
        Map<String,Object> map=new HashMap<>();
        map.put("action", WxActionEnums.GET.getAction());
        map.put("requestdomain",request.getRequestdomainList());
        map.put("wsrequestdomain",request.getWsrequestdomainList());
        map.put("uploaddomain",request.getUploaddomainList());
        map.put("downloaddomain",request.getDownloaddomainList());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo, AppDomainResponse.class);
    }

    /**
     * 获取小程序的业务域名
     *
     * @return web view domain info
     * @throws WxErrorException the wx error exception
     */
    @Override
   public WebViewDomainResponse getWebViewDomainInfo(WebViewDomainRequest request) throws WxErrorException{
        String lastAppLastAccessToken = applicationService.getLastAppLastAccessToken(request.getAppId());
        String targetUrl=API_SET_WEBVIEW_DOMAIN+"?access_token="+lastAppLastAccessToken;
        Map<String,Object> map=new HashMap<>();
        map.put("action",request.getWxActionEnums().getAction());
        map.put("webviewdomain",request.getWebviewDomain());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo, WebViewDomainResponse.class);
    }


//    /**
//     * 获取小程序的信息
//     *
//     * @return the account basic info
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public String getAccountBasicInfo() throws WxErrorException{
//        String targetUrl=API_GET_ACCOUNT_BASICINFO;
//        return null;
//    }

    /**
     * 绑定小程序体验者
     *
     * @ wechatId 体验者微信号（不是openid）
     * @return wx open ma bind tester result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public BindTesterResponse bindTester(BindTesterRequest request) throws WxErrorException{
        String targetUrl=API_BIND_TESTER+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("wechatid",request.getWeChatId());
        String postInfo = postInfo(targetUrl, paramJson);
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
    public UnBindTesterResponse unbindTester(UnBindTesterRequest request) throws WxErrorException{
        String targetUrl=API_UNBIND_TESTER+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("wechatid",request.getAppId());
        String postInfo = postInfo(targetUrl, paramJson);
        return JsonUtils.toBean(postInfo,UnBindTesterResponse.class);
    }

    @Override
    public GetWxTesterResponse getTesterList(GetWxTesterRequest request) throws WxErrorException {
        return null;
    }

//    /**
//     * 解除绑定小程序体验者，其他平台绑定的体验者无法获取到wechatid，可用此方法解绑，详见文档
//     * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/unbind_tester.html
//     *
//     * @param userStr 人员对应的唯一字符串， 可通过获取已绑定的体验者列表获取人员对应的字符串
//     * @return the wx open result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenResult unbindTesterByUserStr(String userStr) throws WxErrorException{
//        return null;
//    }

    /**
     * 获得体验者列表
     *
     * @return the tester list
     * @throws WxErrorException the wx error exception
     */
//    @Override
//    public GetWxTesterResponse getTesterList(GetWxTesterRequest request) throws WxErrorException{
//        return null;
//    }

    /**
     * 设置小程序隐私设置（是否可被搜索）
     *
     * @ status 1表示不可搜索，0表示可搜索
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public ChangeWxaSearchStatusResponse changeWxaSearchStatus(ChangeWxaSearchStatusRequest request) throws WxErrorException{
        String targetUrl=API_CHANGE_WXA_SEARCH_STATUS+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> paramJson=new HashMap<>();
        paramJson.put("status",request.getStatus());
        String postInfo = postInfo(targetUrl, paramJson);
        return   JsonUtils.toBean(postInfo,ChangeWxaSearchStatusResponse.class);
    }

    /**
     * 2. 查询小程序当前隐私设置（是否可被搜索）
     *
     * @return the wxa search status
     * @throws WxErrorException the wx error exception
     */
    @Override
    public WxOpenMaSearchStatusResponse getWxaSearchStatus(WxOpenMaSearchStatusRequest request) throws WxErrorException{
        String targetURl=API_GET_WXA_SEARCH_STATUS+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        String info = getInfo(targetURl, null);
        return JsonUtils.toBean(info,WxOpenMaSearchStatusResponse.class);
    }

//    /**
//     * 3.1 获取展示的公众号信息
//     *
//     * @return the show wxa item
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenMaShowItemResult getShowWxaItem() throws WxErrorException{
//        return null;
//    }


    /**
     * 1、为授权的小程序帐号上传小程序代码
     *
     *  templateId  代码模板ID
     *  userVersion 用户定义版本
     *  userDesc    用户定义版本描述
     *  extInfo     第三方自定义的配置
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public CodeCommitResponse codeCommit(CommitRequest commitRequest) throws WxErrorException{
        String targetUrl=API_CODE_COMMIT+"?access_token="+applicationService.getLastAppLastAccessToken(commitRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("template_id",commitRequest.getTemplateId());
        map.put("user_version",commitRequest.getUserVersion());
        map.put("user_desc",commitRequest.getUserDesc());
        //注意：ext_json必须是字符串类型
        map.put("ext_json",JsonUtils.convertObjectToJSON(commitRequest.getExtInfo()));
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,CodeCommitResponse.class);
    }

    /**
     * 获取体验小程序的体验二维码
     *
     * @param pagePath the page path
     * @param params   the params
     * @return the test qrcode
     * @throws WxErrorException the wx error exception
     */
//    @Override
//    public File getTestQrcode(String pagePath, Map<String, String> params) throws WxErrorException{
//        WxMaQrcodeParam qrcodeParam = WxMaQrcodeParam.create(pagePath);
//        qrcodeParam.addPageParam(params);
//        WxMaService wxMaService = this;
//        return wxMaService.execute(MaQrCodeRequestExecutor.create(getRequestHttp()), API_TEST_QRCODE, qrcodeParam);
//    }

    /**
     * 获取授权小程序帐号的可选类目
     * <p>
     * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
     * </p>
     *
     * @return the category list
     * @throws WxErrorException the wx error exception
     */
    @Override
    public GetAppCategoryListResponse getCategoryList(GetAppCategoryListRequest request) throws WxErrorException{
        String targetUrl=API_GET_CATEGORY+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        return JsonUtils.toBean(info,GetAppCategoryListResponse.class);
    }

    /**
     * 获取已经设置的类目
     * @param request
     * @return
     */
    @Override
    public GetAPPSettedCategoryListResponse getSettedCategoryList(GetAppSettedCategoryListRequest request) {
        String targetUrl=API_GET_SETTED_CATEGORY+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        return  JsonUtils.toBean(info, GetAPPSettedCategoryListResponse.class);

    }

    /**
     * 添加类目
     * @param request
     * @return
     */
    @Override
    public AddAppCategoryResponse addAppCategory(AddAppCategoryRequest request) {
        String targetUrl=API_POST_SET_CATEGORY+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("categories",request.getAddCategoryDto());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,AddAppCategoryResponse.class);
    }

    /**
     * 删除类目
     * @param request
     * @return
     */
    @Override
    public DeleteAppCategoryResponse deleteAppCategory(DeleteAppCategoryRequest request) {
        String targetUrl=API_POST_DELETE_CATEGORY+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("first",request.getFirst());
        map.put("second",request.getSecond());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,DeleteAppCategoryResponse.class);
    }

//    /**
//     * 获取小程序的第三方提交代码的页面配置（仅供第三方开发者代小程序调用）
//     *
//     * @return page list
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenMaPageListResult getPageList() throws WxErrorException{
//        return null;
//    }

    /**
     * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
     *
     * submitAuditMessage the submit audit message
     * @return the wx open ma submit audit result
     * @throws WxErrorException the wx error exception
     */
    @Override
    public SubmitAuditResponse submitAudit(SubmitAuditRequest submitAuditRequest) throws WxErrorException{
        String targetUrl=API_SUBMIT_AUDIT+"?access_token="+applicationService.getLastAppLastAccessToken(submitAuditRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("item_list",submitAuditRequest.getItemList());
        map.put("preview_info",submitAuditRequest.getPreviewInfo());
        map.put("version_desc",submitAuditRequest.getVersionDesc());
        map.put("feedback_info",submitAuditRequest.getFeedbackInfo());
        map.put("feedback_stuff",submitAuditRequest.getFeedbackInfo());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,SubmitAuditResponse.class);
    }

//    /**
//     * 查询某个指定版本的审核状态（仅供第三方代小程序调用）
//     *
//     * @param auditId the auditid
//     * @return the audit status
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenMaQueryAuditResult getAuditStatus(Long auditId) throws WxErrorException{
//        return null;
//    }

    /**
     * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
     *
     * @return 。
     * @throws WxErrorException 。
     */
    @Override
    public GetLastestAuditResponse getLatestAuditStatus(GetLastestAuditRequest request) throws WxErrorException{
        String targetUrl=API_GET_LATEST_AUDIT_STATUS+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        String getInfo = getInfo(targetUrl, null);
        return JsonUtils.toBean(getInfo,GetLastestAuditResponse.class);
    }

    /**
     * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
     * <p>
     * 请填写空的数据包，POST的json数据包为空即可。
     * </p>
     *
     * @return 。
     * @throws WxErrorException 。
     */
    @Override
    public ReleaseAuditedResponse releaseAudited(ReleaseAuditedRequest request) throws WxErrorException{
        String targetUrl=API_RELEASE+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,ReleaseAuditedResponse.class);
    }


//    /**
//     * 10. 修改小程序线上代码的可见状态（仅供第三方代小程序调用）
//     *
//     * @param action the action
//     * @return the wx open result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenResult changeVisitStatus(String action) throws WxErrorException{
//        return null;
//    }

//    /**
//     * 11. 小程序版本回退（仅供第三方代小程序调用）
//     *
//     * @return 。
//     * @throws WxErrorException 。
//     */
//    @Override
//    public WxOpenResult revertCodeRelease() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 15. 小程序审核撤回
//     * <p>
//     * 单个帐号每天审核撤回次数最多不超过1次，一个月不超过10次。
//     * </p>
//     *
//     * @return 。
//     * @throws WxErrorException 。
//     */
//    @Override
//    public WxOpenResult undoCodeAudit() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
//     *
//     * @return 。
//     * @throws WxErrorException 。
//     */
//    @Override
//    public String getSupportVersion() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 12. 查询当前设置的最低基础库版本及各版本用户占比 （仅供第三方代小程序调用）
//     *
//     * @return . support version info
//     * @throws WxErrorException .
//     */
//    @Override
//    public WxOpenMaWeappSupportVersionResult getSupportVersionInfo() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 设置最低基础库版本（仅供第三方代小程序调用）
//     *
//     *  version the version
//     * @return the support version
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public String setSupportVersion(SetLowestSupportRequest request) throws WxErrorException{
//        String targetUrl=API_SET_WEAPP_SUPPORT_VERSION+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
//        Map<String,Object> map=new HashMap<>();
//        map.put("version",request.getAppId());
//        return  postInfo(targetUrl,map);
//    }

    /**
     * 13. 设置最低基础库版本（仅供第三方代小程序调用）
     *
     *  version the version
     * @return support version info
     * @throws WxErrorException the wx error exception
     */
    @Override
    public SetLowestSupportResponse setSupportVersionInfo(SetLowestSupportRequest request) throws WxErrorException{
        String targetUrl=API_SET_WEAPP_SUPPORT_VERSION+"?access_token="+applicationService.getLastAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("version",request.getVersion());
        String postInfo = postInfo(targetUrl, map);
        return  JsonUtils.toBean(postInfo,SetLowestSupportResponse.class);
    }

//    /**
//     * 16. 小程序分阶段发布 - 1)分阶段发布接口
//     *
//     * @param grayPercentage 灰度的百分比，1到100的整数
//     * @return . wx open result
//     * @throws WxErrorException .
//     */
//    @Override
//    public WxOpenResult grayRelease(Integer grayPercentage) throws WxErrorException{
//        return null;
//    }
//
//    /**
//     * 16. 小程序分阶段发布 - 2)取消分阶段发布
//     *
//     * @return . wx open result
//     * @throws WxErrorException .
//     */
//    @Override
//    public WxOpenResult revertGrayRelease() throws WxErrorException{
//        return null;
//    }
//
//    /**
//     * 16. 小程序分阶段发布 - 3)查询当前分阶段发布详情
//     *
//     * @return . gray release plan
//     * @throws WxErrorException .
//     */
//    @Override
//    public WxOpenMaGrayReleasePlanResult getGrayReleasePlan() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 查询服务商的当月提审限额和加急次数（Quota）
//     * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/query_quota.html
//     *
//     * @return the wx open ma query quota result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenMaQueryQuotaResult queryQuota() throws WxErrorException{
//        return null;
//    }

//    /**
//     * 加急审核申请
//     * 有加急次数的第三方可以通过该接口，对已经提审的小程序进行加急操作，加急后的小程序预计2-12小时内审完。
//     *
//     * @param auditId the auditid
//     * @return the boolean
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public Boolean speedAudit(Long auditId) throws WxErrorException{
//        return null;
//    }

//    /**
//     * (1)增加或修改二维码规则
//     *
//     * @param wxQrcodeJumpRule the wx qrcode jump rule
//     * @return the wx open result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenResult addQrcodeJump(WxQrcodeJumpRule wxQrcodeJumpRule) throws WxErrorException{
//        return null;
//    }

//    /**
//     * (2)获取已设置的二维码规则
//     *
//     * @return the qrcode jump
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxGetQrcodeJumpResult getQrcodeJump() throws WxErrorException{
//        return null;
//    }

//    /**
//     * (3)获取校验文件名称及内容
//     *
//     * @return the wx downlooad qrcode jump result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxDownlooadQrcodeJumpResult downloadQrcodeJump() throws WxErrorException{
//        return null;
//    }
//
//    /**
//     * (4)删除已设置的二维码规则
//     *
//     * @param prefix the prefix
//     * @return the wx open result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenResult deleteQrcodeJump(String prefix) throws WxErrorException{
//        return null;
//    }
//
//    /**
//     * (5)发布已设置的二维码规则
//     *
//     * @param prefix the prefix
//     * @return the wx open result
//     * @throws WxErrorException the wx error exception
//     */
//    @Override
//    public WxOpenResult publishQrcodeJump(String prefix) throws WxErrorException{
//        return null;
//    }

    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, null);
    }

    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }

}
