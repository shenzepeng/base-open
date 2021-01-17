package com.kxg.baseopen.provider.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_open_wx_access_token")
public class OpenWxAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限token
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * 过期时间
     */
    @Column(name = "expiration_time")
    private String expirationTime;

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
     * 获取权限token
     *
     * @return access_token - 权限token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置权限token
     *
     * @param accessToken 权限token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * 获取过期时间
     *
     * @return expiration_time - 过期时间
     */
    public String getExpirationTime() {
        return expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @param expirationTime 过期时间
     */
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime == null ? null : expirationTime.trim();
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