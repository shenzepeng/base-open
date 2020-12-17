package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.dto.request.UpLoadMediaFileRequest;
import com.kxg.baseopen.provider.dto.response.UpLoadMediaFileResponse;
import com.kxg.baseopen.provider.handler.FileDirHandler;
import com.kxg.baseopen.provider.openwx.MediaService;

import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 要写注释呀
 */
@Service
@Slf4j
public class MediaServiceImpl implements MediaService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private FileDirHandler fileDir;
    final static Pattern pattern = Pattern.compile("\\S*[?]\\S*");

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
     * @see (String, String, InputStream ) #uploadMedia(String, String, InputStream)
     */
    @Override
    public UpLoadMediaFileResponse uploadMedia(UpLoadMediaFileRequest request) {
        String targetURl=MEDIA_UPLOAD_URL+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId())+"&type="+request.getMediaType();
        //通过url下载文件
        String localFilePath = downByUrl(request.getUrl());
        //通本地文件上传到微信服务器
        String uploadForm = HttpClientUtil.uploadForm(targetURl, "media", localFilePath);
        //删除本地文件
        deleteFile(localFilePath);
        return JsonUtils.toBean(uploadForm, UpLoadMediaFileResponse.class);
    }

    /**
     * 文件下载重试7次
     * @param url
     * @param localFile
     * @throws IOException
     */
    private void saveFile(String url,java.io.File localFile) throws IOException {
        for (int i=0;i<6;i++){
            if (!localFile.exists()){
                try {
                    FileUtils.copyURLToFile(new URL(url), localFile, 200, 2000);
                }catch (Exception e){
                    System.err.println(e);
                }
            }else {
                break;
            }
        }
        if (!localFile.exists()) {
            FileUtils.copyURLToFile(new URL(url), localFile, 200, 2000);
        }
    }


    public String downByUrl(String url) {
        String filePath="";
        try {
            synchronized (url.intern()) {
                String parseSuffix = parseSuffix(url);
                String fileName = UUID.randomUUID().toString() + "." + parseSuffix;
                String imagePath = fileDir.getFileDir() + "/" + fileName;
                java.io.File localFile = new java.io.File(imagePath);
                log.info("fileName {}", fileName);
                // 生成的图片位置
                saveFile(url, localFile);
                log.info("imagePath {}", imagePath);
                //对文件进行修复，有的文件没有后缀，有的胡写，通过文件头还原真正的文件
                //  String fileType = VerifyFileTypeUtils.getFileType(localFile);
                // deleteFile(imagePath);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.isEmpty(filePath)){
            throw new RuntimeException("文件下载失败");
        }
        return filePath;
    }
    /**
     * 获取链接的后缀名
     *
     * @return
     */
    private static String parseSuffix(String url) {

        Matcher matcher = pattern.matcher(url);

        String[] spUrl = url.toString().split("/");
        int len = spUrl.length;
        String endUrl = spUrl[len - 1];

        if (matcher.find()) {
            String[] spEndUrl = endUrl.split("\\?");

            return spEndUrl[0].split("\\.")[1];
        }
        return endUrl.split("\\.")[1];
    }

    private void deleteFile(String filePath) {
        /*
         * 删除当前目录下的test.txt文件
         */
        java.io.File file = new java.io.File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
