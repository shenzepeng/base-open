package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_business_introduce")
public class BusinessIntroduce {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户的id
     */
    @Column(name = "uset_id")
    private Long usetId;

    /**
     * 商家介绍
     */
    private String introduce;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 商家照片
     */
    @Column(name = "img_url")
    private byte[] imgUrl;

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
     * 获取用户的id
     *
     * @return uset_id - 用户的id
     */
    public Long getUsetId() {
        return usetId;
    }

    /**
     * 设置用户的id
     *
     * @param usetId 用户的id
     */
    public void setUsetId(Long usetId) {
        this.usetId = usetId;
    }

    /**
     * 获取商家介绍
     *
     * @return introduce - 商家介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置商家介绍
     *
     * @param introduce 商家介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
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
     * 获取商家照片
     *
     * @return img_url - 商家照片
     */
    public byte[] getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置商家照片
     *
     * @param imgUrl 商家照片
     */
    public void setImgUrl(byte[] imgUrl) {
        this.imgUrl = imgUrl;
    }
}