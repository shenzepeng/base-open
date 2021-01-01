package com.kxg.baseopen.provider.pay.impl;

import com.kxg.baseopen.provider.dao.OrderDao;
import com.kxg.baseopen.provider.dao.OrderIdDao;
import com.kxg.baseopen.provider.pay.WxPayService;
import com.kxg.baseopen.provider.pojo.OrderId;
import com.kxg.baseopen.provider.pojo.OrderInfo;
import com.lly835.bestpay.config.SignType;
import com.lly835.bestpay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 要写注释呀
 */
@Service
public class WxPayServiceImpl implements WxPayService {
    @Autowired
    private OrderIdDao orderIdDao;
    @Override
    public PayResponse pay(PayRequest request) {

        return null;
    }

    @Override
    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        return false;
    }

    @Override
    public PayResponse syncNotify(HttpServletRequest request) {
        return null;
    }

    @Override
    public PayResponse asyncNotify(String notifyData) {
        return null;
    }

    @Override
    public RefundResponse refund(RefundRequest request) {
        return null;
    }

    @Override
    public OrderQueryResponse query(OrderQueryRequest request) {
        return null;
    }

    @Override
    public String downloadBill(DownloadBillRequest request) {
        return null;
    }

    @Override
    public String getQrCodeUrl(String productId) {
        return null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getOrderId() {
        String newOrder = getNewOrder();
        OrderId orderId=new OrderId();
        orderId.setOrderId(newOrder);
        orderIdDao.addOrder(orderId);
        return newOrder;
    }

    private String  getNewOrder(){
        String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
        List<OrderId> orderIds = orderIdDao.orderInfoList(replaceAll);
        if (CollectionUtils.isEmpty(orderIds)){
            return replaceAll;
        }else {
            getNewOrder();
        }
        return "";
    }
}
