package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_save_call_info")
public class SaveCallInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "app_id")
    private String appId;

    private String signature;

    private String timestamp;

    private String nonce;

    private String openid;

    @Column(name = "encType")
    private String enctype;

    @Column(name = "msgSignature")
    private String msgsignature;

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
     * @return request_body
     */
    public String getRequestBody() {
        return requestBody;
    }

    /**
     * @param requestBody
     */
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody == null ? null : requestBody.trim();
    }

    /**
     * @return app_id
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * @return signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     */
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    /**
     * @return timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp == null ? null : timestamp.trim();
    }

    /**
     * @return nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * @param nonce
     */
    public void setNonce(String nonce) {
        this.nonce = nonce == null ? null : nonce.trim();
    }

    /**
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * @return encType
     */
    public String getEnctype() {
        return enctype;
    }

    /**
     * @param enctype
     */
    public void setEnctype(String enctype) {
        this.enctype = enctype == null ? null : enctype.trim();
    }

    /**
     * @return msgSignature
     */
    public String getMsgsignature() {
        return msgsignature;
    }

    /**
     * @param msgsignature
     */
    public void setMsgsignature(String msgsignature) {
        this.msgsignature = msgsignature == null ? null : msgsignature.trim();
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