package com.kxg.baseopen.provider.web.request;

import lombok.Data;


/**
 * 要写注释呀
 */
@Data
public class AddLicenseRequest {
    /**
     * 公司名称
     */
    private String name;

    /**
     * 组织编码

     */
    private String code;

    /**
     * 1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     */
    private String codeType;


    /**
     * 法人微信
     */
    private String legalPersonaWechat;

    /**
     * 法人名称
     */
    private String legalPersonaName;

    /**
     * 营业执照url
     */

    private String imgUrl;

    private Long userId;
}
