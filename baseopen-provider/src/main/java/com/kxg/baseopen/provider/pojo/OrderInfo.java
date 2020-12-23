package com.kxg.baseopen.provider.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Oderid 32位
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 钱数
     */
    @Column(name = "order_money")
    private BigDecimal orderMoney;

    /**
     * 0 未支付  1已支付  2支付失败  3退款
     */
    private Integer status;

    /**
     * 付款人的用户id
     */
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 用户在商户appid下的唯一标识
     */
    @Column(name = "open_id")
    private String openId;

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
     * 获取Oderid 32位
     *
     * @return order_id - Oderid 32位
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置Oderid 32位
     *
     * @param orderId Oderid 32位
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 获取钱数
     *
     * @return order_money - 钱数
     */
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    /**
     * 设置钱数
     *
     * @param orderMoney 钱数
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 获取0 未支付  1已支付  2支付失败  3退款
     *
     * @return status - 0 未支付  1已支付  2支付失败  3退款
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 未支付  1已支付  2支付失败  3退款
     *
     * @param status 0 未支付  1已支付  2支付失败  3退款
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取付款人的用户id
     *
     * @return user_id - 付款人的用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置付款人的用户id
     *
     * @param userId 付款人的用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * 获取用户在商户appid下的唯一标识
     *
     * @return open_id - 用户在商户appid下的唯一标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置用户在商户appid下的唯一标识
     *
     * @param openId 用户在商户appid下的唯一标识
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}