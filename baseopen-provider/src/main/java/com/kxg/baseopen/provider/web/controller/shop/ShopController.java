package com.kxg.baseopen.provider.web.controller.shop;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.ShopService;
import com.kxg.baseopen.provider.web.request.AddShopIntroduceRequest;
import com.kxg.baseopen.provider.web.request.FindShopIntroduceRequest;
import com.kxg.baseopen.provider.web.response.FindShopIntroduceResponse;
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
@RequestMapping("shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    //添加商家信息
    @ApiOperation("添加商家信息")
    @PostMapping("add")
    public SzpJsonResult<IntegerResult> addShop(@RequestBody AddShopIntroduceRequest request){
        return SzpJsonResult.ok(shopService.addShop(request));
    }
    //搜索商家信息
    @ApiOperation("搜索商家信息,通过userId换取")
    @PostMapping("find")
    public SzpJsonResult<FindShopIntroduceResponse> findShop(@RequestBody  FindShopIntroduceRequest request){
        return SzpJsonResult.ok(shopService.findShop(request));
    }
    //搜索商家
//    @PostMapping
    //查看当前商户的下家，待审批 ，已经审批，拒绝
    //查看自身商家上家

}
