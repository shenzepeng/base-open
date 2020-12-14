package com.kxg.baseopen.provider.dto.addcategory;

import lombok.Data;

import java.util.List;

/**
 * 添加类目
 */
@Data
public class AddCategoryDto {
    private List<AddCategoriesInfoDto> categories;
}
