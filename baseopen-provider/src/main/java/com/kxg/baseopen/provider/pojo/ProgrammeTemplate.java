package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_programme_template")
public class ProgrammeTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 方案id
     */
    @Column(name = "programme_id")
    private Long programmeId;

    /**
     * 模板id
     */
    @Column(name = "template_id")
    private Long templateId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 模板描述
     */
    @Column(name = "template_desc")
    private String templateDesc;

    /**
     * 模板版本
     */
    @Column(name = "template_version")
    private String templateVersion;

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
     * 获取方案id
     *
     * @return programme_id - 方案id
     */
    public Long getProgrammeId() {
        return programmeId;
    }

    /**
     * 设置方案id
     *
     * @param programmeId 方案id
     */
    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }

    /**
     * 获取模板id
     *
     * @return template_id - 模板id
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * 设置模板id
     *
     * @param templateId 模板id
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
     * 获取模板描述
     *
     * @return template_desc - 模板描述
     */
    public String getTemplateDesc() {
        return templateDesc;
    }

    /**
     * 设置模板描述
     *
     * @param templateDesc 模板描述
     */
    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc == null ? null : templateDesc.trim();
    }

    /**
     * 获取模板版本
     *
     * @return template_version - 模板版本
     */
    public String getTemplateVersion() {
        return templateVersion;
    }

    /**
     * 设置模板版本
     *
     * @param templateVersion 模板版本
     */
    public void setTemplateVersion(String templateVersion) {
        this.templateVersion = templateVersion == null ? null : templateVersion.trim();
    }
}