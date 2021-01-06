package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_app_template")
public class CodeTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 小程序的appid
     */
    @Column(name = "app_id")
    private Long appId;

    /**
     * 模板id
     */
    @Column(name = "tempate_id")
    private Long tempateId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 当前选择的方案id
     */
    @Column(name = "programme_template_id")
    private Long programmeTemplateId;

    /**
     * 0已提交  1成功  
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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取小程序的appid
     *
     * @return app_id - 小程序的appid
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 设置小程序的appid
     *
     * @param appId 小程序的appid
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 获取模板id
     *
     * @return tempate_id - 模板id
     */
    public Long getTempateId() {
        return tempateId;
    }

    /**
     * 设置模板id
     *
     * @param tempateId 模板id
     */
    public void setTempateId(Long tempateId) {
        this.tempateId = tempateId;
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
     * 获取当前选择的方案id
     *
     * @return programme_template_id - 当前选择的方案id
     */
    public Long getProgrammeTemplateId() {
        return programmeTemplateId;
    }

    /**
     * 设置当前选择的方案id
     *
     * @param programmeTemplateId 当前选择的方案id
     */
    public void setProgrammeTemplateId(Long programmeTemplateId) {
        this.programmeTemplateId = programmeTemplateId;
    }

    /**
     * 获取0已提交  1成功  
     *
     * @return status - 0已提交  1成功  
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0已提交  1成功  
     *
     * @param status 0已提交  1成功  
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}