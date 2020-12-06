package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.common.KxgResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateDraftResponse;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;

import java.util.List;

/**
 * 要写注释呀
 */
public interface OpenWxTemplateService {
    /**
     * 代小程序实现业务.
     * 小程序代码模版库管理：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1506504150_nMMh6&token=&lang=zh_CN
     * access_token 为 component_access_token
     */
    String GET_TEMPLATE_DRAFT_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatedraftlist";
    String GET_TEMPLATE_LIST_URL = "https://api.weixin.qq.com/wxa/gettemplatelist";
    String ADD_TO_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/addtotemplate";
    String DELETE_TEMPLATE_URL = "https://api.weixin.qq.com/wxa/deletetemplate";

    /**
     * 获取草稿箱内的所有临时代码草稿.
     *
     * @return 草稿箱代码模板列表（draftId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    KxgResponse<FindAllTemplateDraftResponse> getTemplateDraftList();

    /**
     * 获取代码模版库中的所有小程序代码模版.
     *
     * @return 小程序代码模版列表（templateId）
     * @throws WxErrorException 获取失败时返回，具体错误码请看此接口的注释文档
     */
    KxgResponse<FindAllTemplateResponse> getTemplateList() ;

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
