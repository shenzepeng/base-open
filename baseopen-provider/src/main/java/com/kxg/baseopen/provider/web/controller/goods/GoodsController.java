package com.kxg.baseopen.provider.web.controller.goods;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.GoodsService;
import com.kxg.baseopen.provider.web.request.FindGoodsRequest;
import com.kxg.baseopen.provider.web.response.FindGoodsResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @ApiOperation("搜索商品")
    @PostMapping("findGoods")
    public SzpJsonResult<FindGoodsResponse> findGoods(@RequestBody FindGoodsRequest request){
        return SzpJsonResult.ok(goodsService.findGoods(request));
    }
}
