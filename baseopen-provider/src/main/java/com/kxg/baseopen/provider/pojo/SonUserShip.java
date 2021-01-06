package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_son_user_ship")
public class SonUserShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 邀请的小城许的openId
     */
    @Column(name = "parent_open_id")
    private String parentOpenId;

    /**
     * 当前小城许的openId
     */
    @Column(name = "son_open_id")
    private String sonOpenId;

    /**
     * 商户的openID
     */
    @Column(name = "mother_open_id")
    private String motherOpenId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "app_id")
    private String appId;

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
     * 获取邀请的小城许的openId
     *
     * @return parent_open_id - 邀请的小城许的openId
     */
    public String getParentOpenId() {
        return parentOpenId;
    }

    /**
     * 设置邀请的小城许的openId
     *
     * @param parentOpenId 邀请的小城许的openId
     */
    public void setParentOpenId(String parentOpenId) {
        this.parentOpenId = parentOpenId == null ? null : parentOpenId.trim();
    }

    /**
     * 获取当前小城许的openId
     *
     * @return son_open_id - 当前小城许的openId
     */
    public String getSonOpenId() {
        return sonOpenId;
    }

    /**
     * 设置当前小城许的openId
     *
     * @param sonOpenId 当前小城许的openId
     */
    public void setSonOpenId(String sonOpenId) {
        this.sonOpenId = sonOpenId == null ? null : sonOpenId.trim();
    }

    /**
     * 获取商户的openID
     *
     * @return mother_open_id - 商户的openID
     */
    public String getMotherOpenId() {
        return motherOpenId;
    }

    /**
     * 设置商户的openID
     *
     * @param motherOpenId 商户的openID
     */
    public void setMotherOpenId(String motherOpenId) {
        this.motherOpenId = motherOpenId == null ? null : motherOpenId.trim();
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
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }
}