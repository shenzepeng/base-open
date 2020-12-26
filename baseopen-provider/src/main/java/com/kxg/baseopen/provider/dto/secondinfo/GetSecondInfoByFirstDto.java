package com.kxg.baseopen.provider.dto.secondinfo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 类目数据结构
 */
@Data
public class GetSecondInfoByFirstDto {
    private List<First> firsts=new ArrayList<>();
}
