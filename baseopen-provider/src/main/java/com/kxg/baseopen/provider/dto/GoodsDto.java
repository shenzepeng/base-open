package com.kxg.baseopen.provider.dto;

import lombok.Data;

import java.util.List;

/**
 * 商品dto
 */
@Data
public class GoodsDto {
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


    private Long userId;


}
