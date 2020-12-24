package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置小程序名称
 */
@Data
public class SettingWxNameResponse {
    /**
     * "errcode":91009,
     * "errmsg":"nickname need proof",
     * "wording":"你申请的名称指向特定城市或地理名称，请提供相应资料证明比如营业执照或商标证等可使用该城市或地理名称作为帐号名称，否则可能审核不通过"
     */
    private String errcode;
    private String errmsg;
    private String wording;
    private String audit_id;

}
