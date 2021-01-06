package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mother_ship")
public class MotherShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 本地
     */
    @Column(name = "local_phone_number")
    private String localPhoneNumber;

    /**
     * 关联
     */
    @Column(name = "link_phone_number")
    private String linkPhoneNumber;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0 待同意 1已同意 2拒绝
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
     * 获取本地
     *
     * @return local_phone_number - 本地
     */
    public String getLocalPhoneNumber() {
        return localPhoneNumber;
    }

    /**
     * 设置本地
     *
     * @param localPhoneNumber 本地
     */
    public void setLocalPhoneNumber(String localPhoneNumber) {
        this.localPhoneNumber = localPhoneNumber == null ? null : localPhoneNumber.trim();
    }

    /**
     * 获取关联
     *
     * @return link_phone_number - 关联
     */
    public String getLinkPhoneNumber() {
        return linkPhoneNumber;
    }

    /**
     * 设置关联
     *
     * @param linkPhoneNumber 关联
     */
    public void setLinkPhoneNumber(String linkPhoneNumber) {
        this.linkPhoneNumber = linkPhoneNumber == null ? null : linkPhoneNumber.trim();
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
     * 获取0 待同意 1已同意 2拒绝
     *
     * @return status - 0 待同意 1已同意 2拒绝
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 待同意 1已同意 2拒绝
     *
     * @param status 0 待同意 1已同意 2拒绝
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}