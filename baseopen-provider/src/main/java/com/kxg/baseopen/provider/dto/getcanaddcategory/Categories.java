package com.kxg.baseopen.provider.dto.getcanaddcategory;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class Categories {
    private int id;
    private List<Integer> children;
    private Qualify qualify;
}
