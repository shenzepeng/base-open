//package com.kxg.baseopen.provider.web.controller.openwx;
//
//import com.kxg.baseopen.provider.config.SzpJsonResult;
//import com.kxg.baseopen.provider.dto.request.UpLoadMediaFileRequest;
//import com.kxg.baseopen.provider.dto.response.UpLoadMediaFileResponse;
//import com.kxg.baseopen.provider.openwx.MediaService;
//import io.swagger.annotations.ApiOperation;
//import me.chanjar.weixin.common.error.WxErrorException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.InputStream;
//
///**
// * 要写注释呀
// */
//@RestController
//@RequestMapping("media")
//public class MediaController {
//    @Autowired
//    private MediaService mediaService;
//    /**
//     * <pre>
//     * 新增临时素材
//     * 小程序可以使用本接口把媒体文件（目前仅支持图片）上传到微信服务器，用户发送客服消息或被动回复用户消息。
//     * 详情请见: <a href="https://mp.weixin.qq.com/debug/wxadoc/dev/api/custommsg/material.html#新增临时素材">新增临时素材</a>
//     * 接口url格式：https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
//     * </pre>
//     *
//     * @ mediaType 媒体类型,
//     * @ file      文件对象
//     * @return the wx media upload result
//     * @throws WxErrorException the wx error exception
//     * @see (String, String, InputStream ) #uploadMedia(String, String, InputStream)
//     */
//    @ApiOperation("新增临时素材")
//    @PostMapping("upload")
//    public SzpJsonResult<UpLoadMediaFileResponse> uploadMedia(@RequestBody UpLoadMediaFileRequest request){
//        return SzpJsonResult.ok(mediaService.uploadMedia(request));
//    }
//
//}
