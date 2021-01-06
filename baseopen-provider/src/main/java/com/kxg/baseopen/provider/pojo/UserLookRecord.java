package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_look_record")
public class UserLookRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 浏览商品的用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商户id
     */
    @Column(name = "business_user_id")
    private Long businessUserId;

    /**
     * 商品详情
     */
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取浏览商品的用户id
     *
     * @return user_id - 浏览商品的用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置浏览商品的用户id
     *
     * @param userId 浏览商品的用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商户id
     *
     * @return business_user_id - 商户id
     */
    public Long getBusinessUserId() {
        return businessUserId;
    }

    /**
     * 设置商户id
     *
     * @param businessUserId 商户id
     */
    public void setBusinessUserId(Long businessUserId) {
        this.businessUserId = businessUserId;
    }

    /**
     * 获取商品详情
     *
     * @return content - 商品详情
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置商品详情
     *
     * @param content 商品详情
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
}