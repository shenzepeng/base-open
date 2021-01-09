package com.kxg.baseopen.provider.web.controller.goods;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.GoodsService;
import com.kxg.baseopen.provider.web.request.AddGoodsRequest;
import com.kxg.baseopen.provider.web.request.DeleteGoodsRequest;
import com.kxg.baseopen.provider.web.request.FindGoodsRequest;
import com.kxg.baseopen.provider.web.request.UpdateGoodsRequest;
import com.kxg.baseopen.provider.web.response.FindGoodsResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;
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
    @ApiOperation("添加商品")
    @PostMapping("addGoods")
    public SzpJsonResult<IntegerResult> addGoods(@RequestBody AddGoodsRequest request){
        return SzpJsonResult.ok(goodsService.addGoods(request));
    }
    @ApiOperation("修改商品")
    @PostMapping("updateGoods")
    public SzpJsonResult<IntegerResult> updateGoods(@RequestBody UpdateGoodsRequest request){
        return SzpJsonResult.ok(goodsService.updateGoods(request));
    }
    @ApiOperation("删除商品")
    @PostMapping("deleteGoods")
    public SzpJsonResult<IntegerResult> deleteGoods(@RequestBody DeleteGoodsRequest request){
        return SzpJsonResult.ok(goodsService.deleteGoodsById(request));
    }
}
