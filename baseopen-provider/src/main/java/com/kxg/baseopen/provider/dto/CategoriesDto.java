package com.kxg.baseopen.provider.dto;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class CategoriesDto {
    /**
     *  "first": 8,
     *       "first_name": "教育",
     *       "second": 39,
     *       "second_name": "出国移民",
     *       "audit_status": 1,
     *       "audit_reason": ""
     */
    private Integer first;
    private String first_name;
    private Integer second;
    private String second_name;
    private Integer audit_status;
    private String audit_reason;
}
