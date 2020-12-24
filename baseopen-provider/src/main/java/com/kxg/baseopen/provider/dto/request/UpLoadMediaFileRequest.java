package com.kxg.baseopen.provider.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 要写注释呀
 */

public class UpLoadMediaFileRequest {
    @Getter
    private String mediaType="image";
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String appId;
}
