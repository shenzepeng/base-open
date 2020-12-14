package com.kxg.baseopen.provider.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetLastestAuditResponse {
    /**
     * 审核编号.
     */

    Long auditid;

    /**
     * 审核状态:2-审核中,0-审核通过,1-审核失败.
     */
    Integer status;

    /**
     * 审核失败原因.
     */
    String reason;
    /**
     * 当status=1，审核被拒绝时，会返回审核失败的小程序截图示例。 xxx丨yyy丨zzz是media_id可通过获取永久素材接口 拉取截图内容）.
     */
    private String screenshot;
}
