package com.kxg.baseopen.provider.dto.response;

import lombok.Data;

/**
 * {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
 */
@Data
public class UpLoadMediaFileResponse {
    private String type;
    private String media_id;
    private long created_at;
}
