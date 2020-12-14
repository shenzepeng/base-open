package com.kxg.baseopen.provider.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kxg.baseopen.provider.enums.WxActionEnums;
import lombok.Data;

import java.util.List;

/**
 * 获取小程序的域名配置
 */
@Data
public class AppDomainRequest {
    private String appId;
    private WxActionEnums actionEnums;
    private List<String> requestdomainList;
    private List<String> wsrequestdomainList;
    private List<String> uploaddomainList;
    private List<String> downloaddomainList;
}
