package com.kxg.baseopen.provider.web.controller.openwx;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.dto.request.CommitRequest;
import com.kxg.baseopen.provider.dto.request.GetLastestAuditRequest;
import com.kxg.baseopen.provider.dto.request.ReleaseAuditedRequest;
import com.kxg.baseopen.provider.dto.request.SubmitAuditRequest;
import com.kxg.baseopen.provider.dto.response.CodeCommitResponse;
import com.kxg.baseopen.provider.dto.response.GetLastestAuditResponse;
import com.kxg.baseopen.provider.dto.response.ReleaseAuditedResponse;
import com.kxg.baseopen.provider.dto.response.SubmitAuditResponse;
import com.kxg.baseopen.provider.openwx.CodeManageService;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("code/manage")
public class CodeManageController {
    @Autowired
    private CodeManageService codeManageService;
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
    @ApiOperation("为授权的小程序帐号上传小程序代码")
    @PostMapping("code/commit")
    public SzpJsonResult<CodeCommitResponse> codeCommit(@RequestBody CommitRequest commitRequest) {
        return SzpJsonResult.ok(codeManageService.codeCommit(commitRequest));
    }


    /**
     * 将第三方提交的代码包提交审核（仅供第三方开发者代小程序调用）
     *
     * @ submitAuditMessage the submit audit message
     * @return the wx open ma submit audit result
     * @throws WxErrorException the wx error exception
     */
    @ApiOperation("将第三方提交的代码包提交审核")
    @PostMapping("submit/audit")
    public SzpJsonResult<SubmitAuditResponse> submitAudit(@RequestBody SubmitAuditRequest request){
        return SzpJsonResult.ok(codeManageService.submitAudit(request));
    }


    /**
     * 8. 查询最新一次提交的审核状态（仅供第三方代小程序调用）
     *
     * @return 。
     * @throws WxErrorException 。
     */
    @ApiOperation("查询最新一次提交的审核状态")
    @PostMapping("get/latest/audit")
    public SzpJsonResult<GetLastestAuditResponse> getLatestAuditStatus(@RequestBody  GetLastestAuditRequest request){
        return SzpJsonResult.ok(codeManageService.getLatestAuditStatus(request));
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
    @ApiOperation("发布已通过审核的小程序")
    @PostMapping("release/audited")
    public SzpJsonResult<ReleaseAuditedResponse> releaseAudited(@RequestBody   ReleaseAuditedRequest request) {
        return SzpJsonResult.ok(codeManageService.releaseAudited(request));
    }


}
