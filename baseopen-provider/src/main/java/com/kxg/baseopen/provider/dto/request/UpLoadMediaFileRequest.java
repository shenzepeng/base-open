package com.kxg.baseopen.provider.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 要写注释呀
 */
@Data
public class UpLoadMediaFileRequest {
    private String mediaType="image";
    private String url;
    private String appId;
}
