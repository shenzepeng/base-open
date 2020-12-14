package com.kxg.baseopen.provider.dto.request;

import com.kxg.baseopen.provider.dto.CategoriesDto;
import com.kxg.baseopen.provider.dto.addcategory.AddCategoryDto;
import lombok.Data;

import java.util.List;

/**
 * 添加类目
 */
@Data
public class AddAppCategoryRequest {
    private String appId;
    private AddCategoryDto addCategoryDto;
}
