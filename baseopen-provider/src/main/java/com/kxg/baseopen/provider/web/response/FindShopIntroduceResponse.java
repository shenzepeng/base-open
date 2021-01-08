package com.kxg.baseopen.provider.web.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 要写注释呀
 */
@Data
public class FindShopIntroduceResponse {
    /**
     * 商家介绍
     */
    private String introduce;


    private Date createTime;

    private Date updateTime;

    /**
     * 商家照片
     */
    private String imgUrl;
}
