package com.kxg.baseopen.provider.dto.getMaBaseInfo;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class Signature_info {
    /**
     *         "signature": "211111111111111111",
     *         "modify_used_count": 1,
     *         "modify_quota": 5
     */
    private String signature;
    private Integer modify_used_count;
    private String modify_quota;
}
