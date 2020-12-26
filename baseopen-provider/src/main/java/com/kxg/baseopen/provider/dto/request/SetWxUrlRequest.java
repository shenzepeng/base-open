package com.kxg.baseopen.provider.dto.request;

import com.kxg.baseopen.provider.enums.WxActionEnums;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class SetWxUrlRequest {
    private String action;
    private String appId;
    private List<String> webviewDomain;
}
