package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class AddShopIntroduceRequest {
    private Long userId;
    private String imgUrl;
    private String introduce;
}
