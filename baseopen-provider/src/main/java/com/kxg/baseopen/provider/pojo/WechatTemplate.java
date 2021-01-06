package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_wechat_template")
public class WechatTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板id
     */
    @Column(name = "templateId")
    private Long templateid;

    /**
     * 模板版本号
     */
    @Column(name = "user_version")
    private String userVersion;

    /**
     * 模板介绍
     */
    @Column(name = "user_desc")
    private String userDesc;

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
     * 获取模板id
     *
     * @return templateId - 模板id
     */
    public Long getTemplateid() {
        return templateid;
    }

    /**
     * 设置模板id
     *
     * @param templateid 模板id
     */
    public void setTemplateid(Long templateid) {
        this.templateid = templateid;
    }

    /**
     * 获取模板版本号
     *
     * @return user_version - 模板版本号
     */
    public String getUserVersion() {
        return userVersion;
    }

    /**
     * 设置模板版本号
     *
     * @param userVersion 模板版本号
     */
    public void setUserVersion(String userVersion) {
        this.userVersion = userVersion == null ? null : userVersion.trim();
    }

    /**
     * 获取模板介绍
     *
     * @return user_desc - 模板介绍
     */
    public String getUserDesc() {
        return userDesc;
    }

    /**
     * 设置模板介绍
     *
     * @param userDesc 模板介绍
     */
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
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