package com.kxg.baseopen.provider.dto.secondinfo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class First {
    private String name;
    private Integer id;
    private List<Children> children=new ArrayList<>();
}
