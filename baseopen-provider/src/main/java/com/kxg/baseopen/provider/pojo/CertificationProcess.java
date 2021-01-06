package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_certification_process")
public class CertificationProcess {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 需要被认证userId
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 0认证小程序   1得待法人认证  2 授权  3设置修改头像名称  4提交审核 5审核结构 失败或者已经发布
     */
    private Integer type;

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
     * 获取需要被认证userId
     *
     * @return user_id - 需要被认证userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置需要被认证userId
     *
     * @param userId 需要被认证userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取0认证小程序   1得待法人认证  2 授权  3设置修改头像名称  4提交审核 5审核结构 失败或者已经发布
     *
     * @return type - 0认证小程序   1得待法人认证  2 授权  3设置修改头像名称  4提交审核 5审核结构 失败或者已经发布
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0认证小程序   1得待法人认证  2 授权  3设置修改头像名称  4提交审核 5审核结构 失败或者已经发布
     *
     * @param type 0认证小程序   1得待法人认证  2 授权  3设置修改头像名称  4提交审核 5审核结构 失败或者已经发布
     */
    public void setType(Integer type) {
        this.type = type;
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