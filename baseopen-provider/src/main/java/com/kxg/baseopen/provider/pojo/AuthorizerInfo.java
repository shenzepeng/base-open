package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_app_authorizer_info")
public class AuthorizerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 被授权的小程序的appid
     */
    @Column(name = "authorizer_appid")
    private String authorizerAppid;

    /**
     * 刷新的权限key
     */
    @Column(name = "authorizer_refresh_token")
    private String authorizerRefreshToken;

    /**
     * 权限集
     */
    @Column(name = "func_info")
    private String funcInfo;

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
     * 获取被授权的小程序的appid
     *
     * @return authorizer_appid - 被授权的小程序的appid
     */
    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    /**
     * 设置被授权的小程序的appid
     *
     * @param authorizerAppid 被授权的小程序的appid
     */
    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid == null ? null : authorizerAppid.trim();
    }

    /**
     * 获取刷新的权限key
     *
     * @return authorizer_refresh_token - 刷新的权限key
     */
    public String getAuthorizerRefreshToken() {
        return authorizerRefreshToken;
    }

    /**
     * 设置刷新的权限key
     *
     * @param authorizerRefreshToken 刷新的权限key
     */
    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
        this.authorizerRefreshToken = authorizerRefreshToken == null ? null : authorizerRefreshToken.trim();
    }

    /**
     * 获取权限集
     *
     * @return func_info - 权限集
     */
    public String getFuncInfo() {
        return funcInfo;
    }

    /**
     * 设置权限集
     *
     * @param funcInfo 权限集
     */
    public void setFuncInfo(String funcInfo) {
        this.funcInfo = funcInfo == null ? null : funcInfo.trim();
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