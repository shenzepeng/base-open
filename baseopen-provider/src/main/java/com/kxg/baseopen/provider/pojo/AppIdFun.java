package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_app_id_fun")
public class AppIdFun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * appId
     */
    @Column(name = "app_id")
    private String appId;

    /**
     * 配置信息
     */
    @Column(name = "fun_info")
    private String funInfo;

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
     * 获取appId
     *
     * @return app_id - appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置appId
     *
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * 获取配置信息
     *
     * @return fun_info - 配置信息
     */
    public String getFunInfo() {
        return funInfo;
    }

    /**
     * 设置配置信息
     *
     * @param funInfo 配置信息
     */
    public void setFunInfo(String funInfo) {
        this.funInfo = funInfo == null ? null : funInfo.trim();
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