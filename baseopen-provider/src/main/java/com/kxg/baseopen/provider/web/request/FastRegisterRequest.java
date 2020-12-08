package com.kxg.baseopen.provider.web.request;

import lombok.Data;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 要写注释呀
 */
@Data
public class FastRegisterRequest {
    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 第三方平台快速创建小程序.
     * 注意：创建任务逻辑串行，单次任务结束后才可以使用相同信息下发第二次任务，请注意规避任务阻塞
     *
     * @param name               企业名（需与工商部门登记信息一致）
     * @param code               企业代码
     * @param codeType           企业代码类型 1：统一社会信用代码（18位） 2：组织机构代码（9位xxxxxxxx-x） 3：营业执照注册号(15位)
     * @param legalPersonaWechat 法人微信号
     * @param legalPersonaName   法人姓名（绑定银行卡）
     * @param componentPhone     第三方联系电话（方便法人与第三方联系）
     * @return .
     * @throws WxErrorException .
     */
    private String name;
    private String code;
    private String codeType;
    private String legalPersonaWechat;
    private String legalPersonaName;
    private String componentPhone;
}
