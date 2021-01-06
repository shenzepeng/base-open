package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_info")
public class GoodsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "front_img")
    private String frontImg;

    /**
     * 视频url
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 9图
     */
    @Column(name = "img_list")
    private String imgList;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "user_id")
    private Long userId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品简介
     *
     * @return introduce - 商品简介
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置商品简介
     *
     * @param introduce 商品简介
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * 获取首页图
     *
     * @return front_img - 首页图
     */
    public String getFrontImg() {
        return frontImg;
    }

    /**
     * 设置首页图
     *
     * @param frontImg 首页图
     */
    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg == null ? null : frontImg.trim();
    }

    /**
     * 获取视频url
     *
     * @return video_url - 视频url
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置视频url
     *
     * @param videoUrl 视频url
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    /**
     * 获取9图
     *
     * @return img_list - 9图
     */
    public String getImgList() {
        return imgList;
    }

    /**
     * 设置9图
     *
     * @param imgList 9图
     */
    public void setImgList(String imgList) {
        this.imgList = imgList == null ? null : imgList.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}