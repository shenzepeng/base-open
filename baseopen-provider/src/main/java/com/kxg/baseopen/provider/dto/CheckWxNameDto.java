package com.kxg.baseopen.provider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 检查微信返回的信息
 */
@Data
public class CheckWxNameDto {
    /**
     * {
     *   "errcode": 0,
     *   "errmsg": "ok",
     *   "hit_condition": true,
     *   "wording": "你申请的名称可能涉及特定主体姓名或名称，请提供可确认主体材料与名称对应的材料，或提供《商标注册证》等其他材料，以证明你有权合理且善意使用该名称，否则可能审核不通过"
     * }
     */
    private String errcode;
    private String errmsg;
    @JsonProperty("hit_condition")
    private String hitCondition;
    private String wording;
}
