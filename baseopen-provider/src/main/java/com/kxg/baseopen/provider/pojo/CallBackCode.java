package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_call_back_code")
public class CallBackCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信授权返回code
     */
    @Column(name = "authorization_code")
    private String authorizationCode;

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
     * 获取微信授权返回code
     *
     * @return authorization_code - 微信授权返回code
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * 设置微信授权返回code
     *
     * @param authorizationCode 微信授权返回code
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode == null ? null : authorizationCode.trim();
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