package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.web.request.AddShopIntroduceRequest;
import com.kxg.baseopen.provider.web.request.FindShopByPhoneNumberRequest;
import com.kxg.baseopen.provider.web.request.FindShopIntroduceRequest;
import com.kxg.baseopen.provider.web.response.FindShopByPhoneNumberResponse;
import com.kxg.baseopen.provider.web.response.FindShopIntroduceResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;

/**
 * 要写注释呀
 */
public interface ShopService {
    IntegerResult addShop(AddShopIntroduceRequest request);
    FindShopIntroduceResponse findShop(FindShopIntroduceRequest request);
    FindShopByPhoneNumberResponse findShopByPhoneNumber(FindShopByPhoneNumberRequest request);
}
