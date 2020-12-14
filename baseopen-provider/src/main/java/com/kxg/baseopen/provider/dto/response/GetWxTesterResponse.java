package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxOpenMaMember;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GetWxTesterResponse {
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;
    @JsonProperty("members")
    private List<WxOpenMaMember> membersList;
}
