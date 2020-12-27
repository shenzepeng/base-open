package com.kxg.baseopen.provider.openwx;

import com.kxg.baseopen.provider.dto.CheckWxNameDto;
import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序基本信息设置
 */
public interface BaseInfoSettingService {
    /**
     * 名称查询
     */
    String CHECK_APP_NAME="https://api.weixin.qq.com/cgi-bin/wxverify/checkwxverifynickname";

    /**
     * 名称设置
     */
    String SET_APP_NAME="https://api.weixin.qq.com/wxa/setnickname";

    /**
     * 绑定微信用户为小程序体验者
     */
    String API_BIND_TESTER = "https://api.weixin.qq.com/wxa/bind_tester";


    /**
     * 解除绑定微信用户为小程序体验者
     */
    String API_UNBIND_TESTER = "https://api.weixin.qq.com/wxa/unbind_tester";


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

    /**
     * 修改服务器地址：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489138143_WPbOO&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String MODIFY_DOMAIN_URL = "https://api.weixin.qq.com/wxa/modify_domain";
    String SET_WEB_VIEW_DOMAIN_URL = "https://api.weixin.qq.com/wxa/setwebviewdomain";
    /**
     * 小程序成员管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1489140588_nVUgx&token=&lang=zh_CN
     * access_token 为 authorizer_access_token
     */
    String BIND_TESTER_URL = "https://api.weixin.qq.com/wxa/bind_tester";
    String UNBIND_TESTER_URL = "https://api.weixin.qq.com/wxa/unbind_tester";

    /**
     * 修改头像
     */
    String CHANGE_IMG_HEADER="https://api.weixin.qq.com/cgi-bin/account/modifyheadimage";
    /**
     * 设置小程序的名称
     * @param request
     * @return
     */
    SettingWxNameResponse setAppName(SettingWxNameRequest request);

    /**
     * 检查小程序的名称
     * @param appId
     * @param name
     * @return
     */
    CheckWxNameDto checkAppName(String appId, String name);


    /**
     * 操作服务器域名
     *
     *   域名操作参数
     *                     除了 webViewDomain，都是有效的
     * @return 以下字段仅在 get 时返回完整字段
     * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
     */
    UpdateWxServerAddressResponse updateWxServiceAddress(UpdateWxServerAddressRequest updateWxServerAddressRequest);

    /**
     * 设置小程序业务域名（仅供第三方代小程序调用）
     * 授权给第三方的小程序，其业务域名只可以为第三方的服务器，
     * 当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
     * 只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
     * 提示：需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
     *
     *  domainAction  域名操作参数
     *                     只有 action 和 webViewDomain 是有效的
     * @return 以下字段仅在 get 时返回完整字段
     * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
     */
    SetWxUrlResponse setWebViewDomain(SetWxUrlRequest request) ;


    /**
     * 绑定小程序体验者
     *
     * @ wechatId 体验者微信号（不是openid）
     * @return wx open ma bind tester result
     * @throws WxErrorException the wx error exception
     */
    BindTesterResponse bindTester(BindTesterRequest request) ;

    /**
     * 解除绑定小程序体验者
     *
     *  wechatId 体验者微信号（不是openid）
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    UnBindTesterResponse unbindTester(UnBindTesterRequest request);


    /**
     * 设置小程序隐私设置（是否可被搜索）
     *
     * @ status 1表示不可搜索，0表示可搜索
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    ChangeWxaSearchStatusResponse changeWxaSearchStatus(ChangeWxaSearchStatusRequest request) ;

    /**
     * 2. 查询小程序当前隐私设置（是否可被搜索）
     *
     * @return the wxa search status
     * @throws WxErrorException the wx error exception
     */
    WxOpenMaSearchStatusResponse getWxaSearchStatus(WxOpenMaSearchStatusRequest request);

    /**
     *  修改头像
     */
    FixHeaderImgResponse fixHeader(FixHeaderImgRequest fixHeaderImgRequest);
}
