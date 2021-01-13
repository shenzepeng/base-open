package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class AddPayEndAndMoneyRequest {
    private Long userId;
    private String orderId;
    private Double money;
}
