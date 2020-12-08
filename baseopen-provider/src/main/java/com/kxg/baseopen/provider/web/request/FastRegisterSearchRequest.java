package com.kxg.baseopen.provider.web.request;

import lombok.Data;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 要写注释呀
 */
@Data
public class FastRegisterSearchRequest {
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
    private String name;
    private String legalPersonaWechat;
    private String legalPersonaName;
}
