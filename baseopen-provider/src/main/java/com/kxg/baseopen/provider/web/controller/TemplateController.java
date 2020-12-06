package com.kxg.baseopen.provider.web.controller;

import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.service.OpenWxTemplateService;
import com.kxg.baseopen.provider.web.response.FindAllTemplateDraftResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("template")
public class TemplateController {
    @Autowired
    private OpenWxTemplateService openWxTemplateService;

    /**
     * 获取草稿箱内的所有临时代码草稿.
     *
     * @return 草稿箱代码模板列表（draftId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    @PostMapping("draft")
    public KxgResponse<FindAllTemplateDraftResponse> getTemplateDraftList(){
        return openWxTemplateService.getTemplateDraftList();
    }

    /**
     * 获取代码模版库中的所有小程序代码模版.
     *
     * @return 小程序代码模版列表（templateId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    @PostMapping("get")
    public KxgResponse<FindAllTemplateResponse> getTemplateList(){
        return openWxTemplateService.getTemplateList();
    }

}
