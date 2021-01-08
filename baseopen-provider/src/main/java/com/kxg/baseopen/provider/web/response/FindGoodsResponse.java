package com.kxg.baseopen.provider.web.response;

import com.kxg.baseopen.provider.dto.GoodsDto;
import com.kxg.baseopen.provider.pojo.GoodsInfo;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindGoodsResponse {
    private List<GoodsDto> goodsDtos;
}
