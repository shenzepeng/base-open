package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_app_authorizer_token")
public class AuthorizerToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 每个小程序的accesstoken

     */
    @Column(name = "authorizer_access_token")
    private String authorizerAccessToken;

    /**
     * 被授权小程序的appid

     */
    @Column(name = "authorizer_appid")
    private String authorizerAppid;

    /**
     * 过期时间
     */
    @Column(name = "expired_time")
    private String expiredTime;

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
     * 获取每个小程序的accesstoken

     *
     * @return authorizer_access_token - 每个小程序的accesstoken

     */
    public String getAuthorizerAccessToken() {
        return authorizerAccessToken;
    }

    /**
     * 设置每个小程序的accesstoken

     *
     * @param authorizerAccessToken 每个小程序的accesstoken

     */
    public void setAuthorizerAccessToken(String authorizerAccessToken) {
        this.authorizerAccessToken = authorizerAccessToken == null ? null : authorizerAccessToken.trim();
    }

    /**
     * 获取被授权小程序的appid

     *
     * @return authorizer_appid - 被授权小程序的appid

     */
    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    /**
     * 设置被授权小程序的appid

     *
     * @param authorizerAppid 被授权小程序的appid

     */
    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid == null ? null : authorizerAppid.trim();
    }

    /**
     * 获取过期时间
     *
     * @return expired_time - 过期时间
     */
    public String getExpiredTime() {
        return expiredTime;
    }

    /**
     * 设置过期时间
     *
     * @param expiredTime 过期时间
     */
    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime == null ? null : expiredTime.trim();
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