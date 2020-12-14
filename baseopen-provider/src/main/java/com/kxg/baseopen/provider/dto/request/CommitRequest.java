package com.kxg.baseopen.provider.dto.request;


import com.kxg.baseopen.provider.dto.WxOpenCommitExtInfo;
import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class CommitRequest {
    private String appId;
    private Long templateId;
    private String userVersion;
    private String userDesc;
    private WxOpenCommitExtInfo extInfo;
}
