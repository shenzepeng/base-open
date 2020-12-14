package com.kxg.baseopen.provider.dto.request;

import com.kxg.baseopen.provider.enums.WxActionEnums;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class WebViewDomainRequest {
    private String appId;
    private WxActionEnums wxActionEnums;
    private List<String> webviewDomain;
}
