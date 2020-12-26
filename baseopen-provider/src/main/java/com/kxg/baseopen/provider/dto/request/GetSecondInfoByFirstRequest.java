package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

/**
 * 通过一级类目名称获取
 */
@Data
public class GetSecondInfoByFirstRequest {
    private String firstName="";
    private String appId;
}
