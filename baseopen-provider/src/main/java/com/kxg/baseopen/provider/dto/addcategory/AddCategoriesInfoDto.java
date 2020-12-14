package com.kxg.baseopen.provider.dto.addcategory;

import lombok.Data;
import java.util.List;
/**
 * 要写注释呀
 */
@Data
public class AddCategoriesInfoDto {
    private int first;
    private int second;
    private List<AddCategoriesInfoKeyValue> certicates;
}
