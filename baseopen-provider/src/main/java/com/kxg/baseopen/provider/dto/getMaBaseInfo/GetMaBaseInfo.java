package com.kxg.baseopen.provider.dto.getMaBaseInfo;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetMaBaseInfo {
    /**
     * Copyright 2020 bejson.com
     */

    /**
     * Auto-generated: 2020-12-27 9:52:20
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    private int errcode;
    private String errmsg;
    private String appid;
    private int account_type;
    private int principal_type;
    private String principal_name;
    private int realname_status;
    private Wx_verify_info wx_verify_info;
    private Signature_info signature_info;
    private Head_image_info head_image_info;
    private String nickname;
    private int registered_country;
    private Nickname_info nickname_info;
}
