package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.ShopIntroduceDao;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.pojo.BusinessIntroduce;
import com.kxg.baseopen.provider.service.ShopService;
import com.kxg.baseopen.provider.web.request.AddShopIntroduceRequest;
import com.kxg.baseopen.provider.web.request.FindShopByPhoneNumberRequest;
import com.kxg.baseopen.provider.web.request.FindShopIntroduceRequest;
import com.kxg.baseopen.provider.web.response.FindShopByPhoneNumberResponse;
import com.kxg.baseopen.provider.web.response.FindShopIntroduceResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 要写注释呀
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopIntroduceDao shopIntroduceDao;
    @Override
    public IntegerResult addShop(AddShopIntroduceRequest request) {
        BusinessIntroduce businessIntroduce=new BusinessIntroduce();
        businessIntroduce.setImgUrl(request.getImgUrl());
        businessIntroduce.setIntroduce(request.getIntroduce());
        businessIntroduce.setUsetId(request.getUserId());
        shopIntroduceDao.addBusiness(businessIntroduce);
        return new IntegerResult();
    }

    @Override
    public FindShopIntroduceResponse findShop(FindShopIntroduceRequest request) {
        List<BusinessIntroduce> shopIntroduce = shopIntroduceDao.findShopIntroduce(request.getUserId());
        if (CollectionUtils.isEmpty(shopIntroduce)){
            throw new KxgException("99999","当前商户介绍为空");
        }
        FindShopIntroduceResponse response=new FindShopIntroduceResponse();
        BeanUtils.copyProperties(shopIntroduce.get(0),response);
        return response;
    }

    @Override
    public FindShopByPhoneNumberResponse findShopByPhoneNumber(FindShopByPhoneNumberRequest request) {

        return null;
    }
}
