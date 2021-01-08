package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.pojo.GoodsInfo;
import com.kxg.baseopen.provider.web.request.AddGoodsRequest;
import com.kxg.baseopen.provider.web.request.FindGoodsRequest;
import com.kxg.baseopen.provider.web.response.FindGoodsResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;

/**
 * 要写注释呀
 */
public interface GoodsService {
    IntegerResult addGoods(AddGoodsRequest request);
    FindGoodsResponse findGoods(FindGoodsRequest request);
}
