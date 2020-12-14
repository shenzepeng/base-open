package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kxg.baseopen.provider.dto.CategoriesDto;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxOpenMaCategory;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GetAPPSettedCategoryListResponse {

//    参数	类型	说明
//    errcode	number	返回码
//    errmsg	string	错误信息
//    categories	object array	已设置的类目信息列表
//    limit	number	一个更改周期内可以添加类目的次数
//    quota	number	本更改周期内还可以添加类目的次数
//    category_limit	number	最多可以设置的类目数量
    private String errcode;
    private String errmsg;
    private List<CategoriesDto> categories;
    private Integer limit;
    private Integer quota;
    private Integer category_limit;
}
