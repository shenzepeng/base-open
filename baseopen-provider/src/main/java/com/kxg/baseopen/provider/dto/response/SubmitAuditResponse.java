package com.kxg.baseopen.provider.dto.response;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class SubmitAuditResponse {
    /**
     * "errcode": 0,
     *   "errmsg": "ok",
     */
    private String errcode;
    private String errmsg;
    private Long auditid;
}
