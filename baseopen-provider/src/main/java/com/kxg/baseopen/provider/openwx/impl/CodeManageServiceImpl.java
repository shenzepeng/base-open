package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.dto.request.CommitRequest;
import com.kxg.baseopen.provider.dto.request.GetLastestAuditRequest;
import com.kxg.baseopen.provider.dto.request.ReleaseAuditedRequest;
import com.kxg.baseopen.provider.dto.request.SubmitAuditRequest;
import com.kxg.baseopen.provider.dto.response.CodeCommitResponse;
import com.kxg.baseopen.provider.dto.response.GetLastestAuditResponse;
import com.kxg.baseopen.provider.dto.response.ReleaseAuditedResponse;
import com.kxg.baseopen.provider.dto.response.SubmitAuditResponse;
import com.kxg.baseopen.provider.openwx.CodeManageService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 要写注释呀
 */
@Service
public class CodeManageServiceImpl implements CodeManageService {
    @Autowired
    private TokenService tokenService;

    @Override
    public CodeCommitResponse codeCommit(CommitRequest commitRequest)  {
        String targetUrl=API_CODE_COMMIT+"?access_token="+tokenService.getSmallAppLastAccessToken(commitRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("template_id",commitRequest.getTemplateId());
        map.put("user_version",commitRequest.getUserVersion());
        map.put("user_desc",commitRequest.getUserDesc());
        //注意：ext_json必须是字符串类型
        map.put("ext_json", JsonUtils.convertObjectToJSON(commitRequest.getExtInfo()));
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,CodeCommitResponse.class);
    }

    @Override
    public SubmitAuditResponse submitAudit(SubmitAuditRequest submitAuditRequest) {
        String targetUrl=API_SUBMIT_AUDIT+"?access_token="+tokenService.getSmallAppLastAccessToken(submitAuditRequest.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("item_list",submitAuditRequest.getItemList());
        map.put("preview_info",submitAuditRequest.getPreviewInfo());
        map.put("version_desc",submitAuditRequest.getVersionDesc());
        map.put("feedback_info",submitAuditRequest.getFeedbackInfo());
        map.put("feedback_stuff",submitAuditRequest.getFeedbackInfo());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,SubmitAuditResponse.class);

    }

    @Override
    public GetLastestAuditResponse getLatestAuditStatus(GetLastestAuditRequest request)  {
        String targetUrl=API_GET_LATEST_AUDIT_STATUS+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String getInfo = getInfo(targetUrl, null);
        return JsonUtils.toBean(getInfo,GetLastestAuditResponse.class);
    }

    @Override
    public ReleaseAuditedResponse releaseAudited(ReleaseAuditedRequest request)  {
        String targetUrl=API_RELEASE+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,ReleaseAuditedResponse.class);
    }


    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, null);
    }

    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }
}

