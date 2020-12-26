package com.kxg.baseopen.provider.dto.getcanaddcategory;

import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class Categories {
    private Integer id;
    private List<Integer> children;
    private Qualify qualify;
    private String name;
    private Integer father;
    private Integer level;
}
