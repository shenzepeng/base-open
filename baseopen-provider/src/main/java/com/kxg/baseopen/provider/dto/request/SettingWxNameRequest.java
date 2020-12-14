package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class SettingWxNameRequest {
    private String nickName;
    private String license;
    private List<String> namingOtherStuff;
    private String appId;
}
