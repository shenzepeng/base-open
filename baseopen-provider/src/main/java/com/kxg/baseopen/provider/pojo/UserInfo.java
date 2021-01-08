package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小程序的appid
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 小程序的openID

     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 头像
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 信息
     */
    @Column(name = "info_msg")
    private String infoMsg;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0 客户 1商户  2代理
     */
    private Integer status;

    /**
     * 档前商户可以招揽多少代理初始20个 付费增加数量   添加代理减少数量
     */
    @Column(name = "number_counts")
    private Integer numberCounts;

    /**
     * 年费过期时间
     */
    @Column(name = "expire_time")
    private String expireTime;

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
     * 获取小程序的appid
     *
     * @return app_id - 小程序的appid
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置小程序的appid
     *
     * @param appId 小程序的appid
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取小程序的openID

     *
     * @return open_id - 小程序的openID

     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置小程序的openID

     *
     * @param openId 小程序的openID

     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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
     * 获取头像
     *
     * @return img_url - 头像
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置头像
     *
     * @param imgUrl 头像
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 获取信息
     *
     * @return info_msg - 信息
     */
    public String getInfoMsg() {
        return infoMsg;
    }

    /**
     * 设置信息
     *
     * @param infoMsg 信息
     */
    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg == null ? null : infoMsg.trim();
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
     * 获取0 客户 1商户  2代理
     *
     * @return status - 0 客户 1商户  2代理
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 客户 1商户  2代理
     *
     * @param status 0 客户 1商户  2代理
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取档前商户可以招揽多少代理初始20个 付费增加数量   添加代理减少数量
     *
     * @return number_counts - 档前商户可以招揽多少代理初始20个 付费增加数量   添加代理减少数量
     */
    public Integer getNumberCounts() {
        return numberCounts;
    }

    /**
     * 设置档前商户可以招揽多少代理初始20个 付费增加数量   添加代理减少数量
     *
     * @param numberCounts 档前商户可以招揽多少代理初始20个 付费增加数量   添加代理减少数量
     */
    public void setNumberCounts(Integer numberCounts) {
        this.numberCounts = numberCounts;
    }

    /**
     * 获取年费过期时间
     *
     * @return expire_time - 年费过期时间
     */
    public String getExpireTime() {
        return expireTime;
    }

    /**
     * 设置年费过期时间
     *
     * @param expireTime 年费过期时间
     */
    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime == null ? null : expireTime.trim();
    }
}