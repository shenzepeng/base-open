package com.kxg.baseopen.provider.openwx;

import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import com.kxg.baseopen.provider.web.response.FindAllTemplateDraftResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 要写注释呀
 */
public interface CodeManageService {
    /**
     * 代小程序实现业务.
     * 小程序代码模版库管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1506504150_nMMh6&token=&lang=zh_CN
     * access_token 为 component_access_token
     */
    String GET_TEMPLATE_DRAFT_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatedraftlist";
    String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatelist";
    String ADD_TO_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/addtotemplate";
    String DELETE_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/deletetemplate";

    //
//     * 1. 为授权的小程序帐号上传小程序代码
//     */
    String API_CODE_COMMIT = "https://api.weixin.qq.com/wxa/commit";
    /**
     * 5. 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
     */
    String API_SUBMIT_AUDIT = "https://api.weixin.qq.com/wxa/submit_audit";
    /**
     * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
     */
    String API_GET_LATEST_AUDIT_STATUS = "https://api.weixin.qq.com/wxa/get_latest_auditstatus";
    /**
     * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
     */
    String API_RELEASE = "https://api.weixin.qq.com/wxa/release";
    /**
     * 1、为授权的小程序帐号上传小程序代码
     * <p>
     * templateId  代码模板ID
     * userVersion 用户定义版本
     * userDesc    用户定义版本描述
     * extInfo     第三方自定义的配置
     * the wx open result
     * WxErrorException the wx error exception
     */
    CodeCommitResponse codeCommit(CommitRequest commitRequest) ;


    /**
     * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
     *
     * @ submitAuditMessage the submit audit message
     * @return the wx open ma submit audit result
     * @throws WxErrorException the wx error exception
     */
    SubmitAuditResponse submitAudit(SubmitAuditRequest request) ;


    /**
     * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
     *
     * @return 。
     * @throws WxErrorException 。
     */
    GetLastestAuditResponse getLatestAuditStatus(GetLastestAuditRequest request);

    /**
     * 9. 发布已通过审核的小程序（仅供第三方代小程序调用）
     * <p>
     * 请填写空的数据包，POST的json数据包为空即可。
     * </p>
     *
     * @return 。
     * @throws WxErrorException 。
     */
    ReleaseAuditedResponse releaseAudited(ReleaseAuditedRequest request);


    /**
     * 获取草稿箱内的所有临时代码草稿.
     *
     * @return 草稿箱代码模板列表（draftId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    FindAllTemplateDraftResponse getTemplateDraftList();

    /**
     * 获取代码模版库中的所有小程序代码模版.
     *
     * @return 小程序代码模版列表（templateId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    FindAllTemplateResponse getTemplateList() ;

    /**
     * 将草稿箱的草稿选为小程序代码模版.
     *
     * @param draftId 草稿ID，本字段可通过“获取草稿箱内的所有临时代码草稿”接口获得
     * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
     * @see #getTemplateDraftList
     */
    void addToTemplate(long draftId) throws WxErrorException;

    /**
     * 删除指定小程序代码模版.
     *
     * @param templateId 要删除的模版ID
     * @throws WxErrorException 操作失败时抛出，具体错误码请看此接口的注释文档
     * @see #getTemplateList
     */
    void deleteTemplate(long templateId) throws WxErrorException;


}
