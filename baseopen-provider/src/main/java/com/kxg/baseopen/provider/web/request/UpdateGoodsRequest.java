package com.kxg.baseopen.provider.web.request;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class UpdateGoodsRequest {
    private Long id;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品简介
     */
    private String introduce;

    /**
     * 首页图
     */
    private String frontImg;

    /**
     * 视频url
     */

    private String videoUrl;

    /**
     * 9图
     */

    private List<String> imgList;



}
