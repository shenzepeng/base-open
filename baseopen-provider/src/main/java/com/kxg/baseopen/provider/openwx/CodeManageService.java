package com.kxg.baseopen.provider.openwx;

import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 要写注释呀
 */
public interface CodeManageService {
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



}
