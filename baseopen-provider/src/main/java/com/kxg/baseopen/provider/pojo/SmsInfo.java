package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sms_info")
public class SmsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String code;

    @Column(name = "vetify_times")
    private Integer vetifyTimes;

    @Column(name = "flow_msg")
    private String flowMsg;

    @Column(name = "flow_id")
    private String flowId;

    @Column(name = "flow_code")
    private String flowCode;

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
     * @return phone_number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return vetify_times
     */
    public Integer getVetifyTimes() {
        return vetifyTimes;
    }

    /**
     * @param vetifyTimes
     */
    public void setVetifyTimes(Integer vetifyTimes) {
        this.vetifyTimes = vetifyTimes;
    }

    /**
     * @return flow_msg
     */
    public String getFlowMsg() {
        return flowMsg;
    }

    /**
     * @param flowMsg
     */
    public void setFlowMsg(String flowMsg) {
        this.flowMsg = flowMsg == null ? null : flowMsg.trim();
    }

    /**
     * @return flow_id
     */
    public String getFlowId() {
        return flowId;
    }

    /**
     * @param flowId
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    /**
     * @return flow_code
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * @param flowCode
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
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