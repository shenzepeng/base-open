package com.kxg.baseopen.provider.dto.request;

import com.kxg.baseopen.provider.enums.WxActionEnums;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class UpdateWxServerAddressRequest {
    private WxActionEnums action;
    private List<String> requestDomain;
    private List<String> wsRequestDomain;
    private List<String> uploadDomain;
    private List<String> downloadDomain;
    private String appId;
}
