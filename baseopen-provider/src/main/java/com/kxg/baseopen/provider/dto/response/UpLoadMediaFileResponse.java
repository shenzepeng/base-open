package com.kxg.baseopen.provider.dto.response;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class UpLoadMediaFileResponse {
    private String url;
    private String type;
    private String mediaId;
    private String thumbMediaId;
    private long createdAt;
}
