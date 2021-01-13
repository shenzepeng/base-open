package com.kxg.baseopen.provider.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_wx_fast_register")
public class WxFastRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 组织编码 

     */
    private String code;

    /**
     * 1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     */
    @Column(name = "code_type")
    private String codeType;

    /**
     * 第三方手机号
     */
    @Column(name = "component_phone")
    private String componentPhone;

    /**
     * 法人微信
     */
    @Column(name = "legal_persona_wechat")
    private String legalPersonaWechat;

    /**
     * 法人名称
     */
    @Column(name = "legal_persona_name")
    private String legalPersonaName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 0认证中
     */
    private Integer status;

    @Column(name = "err_code")
    private String errCode;

    @Column(name = "error_msg")
    private String errorMsg;

    /**
     * 营业执照url
     */
    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "user_id")
    private Long userId;

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
     * 获取公司名称
     *
     * @return name - 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置公司名称
     *
     * @param name 公司名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取组织编码 

     *
     * @return code - 组织编码 

     */
    public String getCode() {
        return code;
    }

    /**
     * 设置组织编码 

     *
     * @param code 组织编码 

     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     *
     * @return code_type - 1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * 设置1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     *
     * @param codeType 1 统一社会信用代码  18位  2 组织机构代码 9位xxxxxxxx x   3 营业执照注册号 15位
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    /**
     * 获取第三方手机号
     *
     * @return component_phone - 第三方手机号
     */
    public String getComponentPhone() {
        return componentPhone;
    }

    /**
     * 设置第三方手机号
     *
     * @param componentPhone 第三方手机号
     */
    public void setComponentPhone(String componentPhone) {
        this.componentPhone = componentPhone == null ? null : componentPhone.trim();
    }

    /**
     * 获取法人微信
     *
     * @return legal_persona_wechat - 法人微信
     */
    public String getLegalPersonaWechat() {
        return legalPersonaWechat;
    }

    /**
     * 设置法人微信
     *
     * @param legalPersonaWechat 法人微信
     */
    public void setLegalPersonaWechat(String legalPersonaWechat) {
        this.legalPersonaWechat = legalPersonaWechat == null ? null : legalPersonaWechat.trim();
    }

    /**
     * 获取法人名称
     *
     * @return legal_persona_name - 法人名称
     */
    public String getLegalPersonaName() {
        return legalPersonaName;
    }

    /**
     * 设置法人名称
     *
     * @param legalPersonaName 法人名称
     */
    public void setLegalPersonaName(String legalPersonaName) {
        this.legalPersonaName = legalPersonaName == null ? null : legalPersonaName.trim();
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
     * 获取0认证中
     *
     * @return status - 0认证中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0认证中
     *
     * @param status 0认证中
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return err_code
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * @param errCode
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode == null ? null : errCode.trim();
    }

    /**
     * @return error_msg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    /**
     * 获取营业执照url
     *
     * @return img_url - 营业执照url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置营业执照url
     *
     * @param imgUrl 营业执照url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}