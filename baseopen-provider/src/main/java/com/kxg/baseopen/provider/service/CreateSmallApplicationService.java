package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.web.request.FastRegisterRequest;
import com.kxg.baseopen.provider.web.request.FastRegisterSearchRequest;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * 要写注释呀
 */
public interface CreateSmallApplicationService {

    /**
     * 快速创建小程序接口.
     */
    String FAST_REGISTER_WEAPP_URL = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=create";
    String FAST_REGISTER_WEAPP_SEARCH_URL = "https://api.weixin.qq.com/cgi-bin/component/fastregisterweapp?action=search";
    /**
     * 预售权码
     */
    String PRE_AUTH_CODE="https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode";
    /**
     * 生成客户确认的URL
     */
    String MAKE_SURE_URL="https://mp.weixin.qq.com/safe/bindcomponent?action=bindcomponent&no_scan=1";
    /**
     * 通过微信回调
     * auth_code 获取微信的
     * refresh token
     */
    String GET_APP_REFRESH_CODE="https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=";
    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 第三方平台快速创建小程序.
     * 注意：创建任务逻辑串行，单次任务结束后才可以使用相同信息下发第二次任务，请注意规避任务阻塞
     *
     * @ name               企业名（需与工商部门登记信息一致）
     * @ code               企业代码
     * @ codeType           企业代码类型 1：统一社会信用代码（18位） 2：组织机构代码（9位xxxxxxxx-x） 3：营业执照注册号(15位)
     *  legalPersonaWechat 法人微信号
     * @paam legalPersonaName   法人姓名（绑定银行卡）
     * @ componentPhone     第三方联系电话（方便法人与第三方联系）
     * @return .
     * @throws WxErrorException .
     */
    WxOpenResult fastRegisterWeapp(FastRegisterRequest registerRequest) ;

    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 查询第三方平台快速创建小程序的任务状态
     * 注意：该接口只提供当下任务结果查询，不建议过分依赖该接口查询所创建小程序。
     * 小程序的成功状态可在第三方服务器中自行对账、查询。
     * 不要频繁调用search接口，消息接收需通过服务器查看。调用search接口会消耗接口整体调用quato
     *
     * @ name               企业名（需与工商部门登记信息一致）
     * @ legalPersonaWechat 法人微信号
     * @ legalPersonaName   法人姓名（绑定银行卡）
     * @throws WxErrorException .
     */
    WxOpenResult fastRegisterWeappSearch(FastRegisterSearchRequest registerSearchRequest) ;


    /**
     * 获取客户授权的链接
     * @param appId
     * @return
     */
    String getCustomerMakeSureUrl(String appId);

    String getCallBackUrl(String authCode,Integer expiredTime);
}
