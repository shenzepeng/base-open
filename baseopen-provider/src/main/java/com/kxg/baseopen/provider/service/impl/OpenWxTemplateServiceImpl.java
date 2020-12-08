package com.kxg.baseopen.provider.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.OpenWxService;
import com.kxg.baseopen.provider.service.OpenWxTemplateService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.web.response.FindAllTemplateDraftResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class OpenWxTemplateServiceImpl implements OpenWxTemplateService {
    private static final JsonParser JSON_PARSER = new JsonParser();

    @Autowired
    private OpenWxService openWxService;
    @Override
    public FindAllTemplateDraftResponse getTemplateDraftList() {
        FindAllTemplateDraftResponse findAllTemplateDraftResponse=new FindAllTemplateDraftResponse();
        String responseContent = getInfo(GET_TEMPLATE_DRAFT_LIST_URL);
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
        String responseContent = getInfo(GET_TEMPLATE_LIST_URL);
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
        Map<String,Object> templateMap=new HashMap<>();
        templateMap.put("draft_id",draftId);
        postInfo(ADD_TO_TEMPLATE_URL,templateMap);
    }

    @Override
    public void deleteTemplate(long templateId) throws WxErrorException {
        Map<String,Object> map=new HashMap<>();
        map.put("template_id",templateId);
        postInfo(DELETE_TEMPLATE_URL,map);
    }

    private String getInfo(String targetUrl){
        String accessToken = openWxService.getAccessToken();
        HashMap<String,String> accessTokenMap=new HashMap<>();
        accessTokenMap.put("access_token",accessToken);
        return HttpClientUtil.get(targetUrl, accessTokenMap);
    }

    private String postInfo(String targetUrl,Map<String,Object> bodyMsg){
        String accessToken = openWxService.getAccessToken();
        HashMap<String,String> accessTokenMap=new HashMap<>();
        accessTokenMap.put("access_token",accessToken);
        return HttpClientUtil.postJson(targetUrl,bodyMsg, accessTokenMap);
    }

}
