package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_apply")
public class UserApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 申请的用户的userId
     */
    @Column(name = "local_user_id")
    private Long localUserId;

    /**
     * 统一的userId
     */
    @Column(name = "romete_user_id")
    private Long rometeUserId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0 未审批 1  统一  2拒绝
     */
    private Integer status;

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
     * 获取申请的用户的userId
     *
     * @return local_user_id - 申请的用户的userId
     */
    public Long getLocalUserId() {
        return localUserId;
    }

    /**
     * 设置申请的用户的userId
     *
     * @param localUserId 申请的用户的userId
     */
    public void setLocalUserId(Long localUserId) {
        this.localUserId = localUserId;
    }

    /**
     * 获取统一的userId
     *
     * @return romete_user_id - 统一的userId
     */
    public Long getRometeUserId() {
        return rometeUserId;
    }

    /**
     * 设置统一的userId
     *
     * @param rometeUserId 统一的userId
     */
    public void setRometeUserId(Long rometeUserId) {
        this.rometeUserId = rometeUserId;
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
     * 获取0 未审批 1  统一  2拒绝
     *
     * @return status - 0 未审批 1  统一  2拒绝
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 未审批 1  统一  2拒绝
     *
     * @param status 0 未审批 1  统一  2拒绝
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}