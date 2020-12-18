package com.kxg.baseopen.provider.openwx.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
import com.kxg.baseopen.provider.web.response.FindAllTemplateDraftResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class CodeManageServiceImpl implements CodeManageService {
    private static final JsonParser JSON_PARSER = new JsonParser();

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



    @Override
    public FindAllTemplateDraftResponse getTemplateDraftList() {
        FindAllTemplateDraftResponse findAllTemplateDraftResponse=new FindAllTemplateDraftResponse();
        String targetUrl=GET_TEMPLATE_DRAFT_LIST_URL+"?access_token="+tokenService.getComponentAccessToken();
        String responseContent = getInfo(targetUrl,null);
        JsonObject response = JSON_PARSER.parse(StringUtils.defaultString(responseContent, "{}")).getAsJsonObject();
        boolean hasDraftList = response.has("draft_list");
        if (hasDraftList) {
            List<WxOpenMaCodeTemplate> wxOpenMaCodeTemplates= WxOpenGsonBuilder.create().fromJson(response.getAsJsonArray("draft_list"),
                    new TypeToken<List<WxOpenMaCodeTemplate>>() {}.getType());
            findAllTemplateDraftResponse.setWxOpenMaCodeTemplateList(wxOpenMaCodeTemplates);
            log.info("findAllTemplateDraftResponse {}",findAllTemplateDraftResponse);
        }
        return findAllTemplateDraftResponse;
    }


    @Override
    public FindAllTemplateResponse getTemplateList()  {
        FindAllTemplateResponse findAllTemplateResponse=new FindAllTemplateResponse();
        String targetUrl=GET_TEMPLATE_LIST_URL+"?access_token="+tokenService.getComponentAccessToken();
        String responseContent = getInfo(targetUrl,null);
        JsonObject response = JSON_PARSER.parse(StringUtils.defaultString(responseContent, "{}")).getAsJsonObject();
        boolean hasTemplateList = response.has("template_list");
        if (hasTemplateList) {
            List<WxOpenMaCodeTemplate> wxOpenMaCodeTemplates=WxOpenGsonBuilder.create().fromJson(response.getAsJsonArray("template_list"),
                    new TypeToken<List<WxOpenMaCodeTemplate>>() {
                    }.getType());
            findAllTemplateResponse.setWxOpenMaCodeTemplateList(wxOpenMaCodeTemplates);
            log.info("findAllTemplateResponse {}",findAllTemplateResponse);
        }
        return findAllTemplateResponse;
    }
    @Override
    public void addToTemplate(long draftId) throws WxErrorException {
        String targetUrl=ADD_TO_TEMPLATE_URL+"?access_token="+tokenService.getComponentAccessToken();
        Map<String,Object> templateMap=new HashMap<>();
        templateMap.put("draft_id",draftId);
        postInfo(targetUrl,templateMap);
    }

    @Override
    public void deleteTemplate(long templateId) throws WxErrorException {
        String targetUrl=DELETE_TEMPLATE_URL+"?access_token="+tokenService.getComponentAccessToken();
        Map<String,Object> map=new HashMap<>();
        map.put("template_id",templateId);
        postInfo(targetUrl,map);
    }




    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, null);
    }

    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }
}

