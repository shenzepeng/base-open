package com.kxg.baseopen.provider.pay.impl;

import com.kxg.baseopen.provider.dao.OrderDao;
import com.kxg.baseopen.provider.pay.WxPayService;
import com.lly835.bestpay.config.SignType;
import com.lly835.bestpay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 要写注释呀
 */
@Service
public class WxPayServiceImpl implements WxPayService {
    @Autowired
    private OrderDao orderDao;
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
}
