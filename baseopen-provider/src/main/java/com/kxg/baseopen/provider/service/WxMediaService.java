package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.dto.request.UpLoadMediaFileRequest;
import com.kxg.baseopen.provider.dto.response.UpLoadMediaFileResponse;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 要写注释呀
 */
public interface WxMediaService {
    String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?type=%s";
    String MEDIA_GET_URL = "https://api.weixin.qq.com/cgi-bin/media/get";

    /**
     * <pre>
     * 新增临时素材
     * 小程序可以使用本接口把媒体文件（目前仅支持图片）上传到微信服务器，用户发送客服消息或被动回复用户消息。
     * 详情请见: <a href="https://mp.weixin.qq.com/debug/wxadoc/dev/api/custommsg/material.html#新增临时素材">新增临时素材</a>
     * 接口url格式：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     * </pre>
     *
     * @ mediaType 媒体类型,
     * @ file      文件对象
     * @return the wx media upload result
     * @throws WxErrorException the wx error exception
     * @see (String, String, InputStream) #uploadMedia(String, String, InputStream)
     */
    UpLoadMediaFileResponse uploadMedia(UpLoadMediaFileRequest request) throws WxErrorException;

}
