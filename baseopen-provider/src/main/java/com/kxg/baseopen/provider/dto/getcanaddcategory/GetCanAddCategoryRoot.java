package com.kxg.baseopen.provider.dto.getcanaddcategory;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetCanAddCategoryRoot {
    private int errcode;
    private String errmsg;
    private CategoriesList categories_list;
}
