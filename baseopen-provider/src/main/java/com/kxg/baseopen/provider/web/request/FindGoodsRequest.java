package com.kxg.baseopen.provider.web.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class FindGoodsRequest {
    private String goodName;
    private Long userId;
    private Integer pageNumber=10;
    private Integer pageSize=1;
}
