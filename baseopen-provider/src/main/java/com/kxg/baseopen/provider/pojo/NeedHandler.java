package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_need_handler")
public class NeedHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * userId
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 0未开始创建（信息没填） 1法人未授权  2没头像等上传资料 3审核中 4审核成功
     */
    private Integer status;

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
     * 获取userId
     *
     * @return user_id - userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置userId
     *
     * @param userId userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取0未开始创建（信息没填） 1法人未授权  2没头像等上传资料 3审核中 4审核成功
     *
     * @return status - 0未开始创建（信息没填） 1法人未授权  2没头像等上传资料 3审核中 4审核成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0未开始创建（信息没填） 1法人未授权  2没头像等上传资料 3审核中 4审核成功
     *
     * @param status 0未开始创建（信息没填） 1法人未授权  2没头像等上传资料 3审核中 4审核成功
     */
    public void setStatus(Integer status) {
        this.status = status;
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