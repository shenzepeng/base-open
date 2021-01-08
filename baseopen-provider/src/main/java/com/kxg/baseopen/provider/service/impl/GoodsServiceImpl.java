package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.GoodsDao;
import com.kxg.baseopen.provider.dto.GoodsDto;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.pojo.GoodsInfo;
import com.kxg.baseopen.provider.service.GoodsService;
import com.kxg.baseopen.provider.utils.JsonUtils;
import com.kxg.baseopen.provider.web.request.AddGoodsRequest;
import com.kxg.baseopen.provider.web.request.FindGoodsRequest;
import com.kxg.baseopen.provider.web.response.FindGoodsResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public IntegerResult addGoods(AddGoodsRequest request) {
        if (StringUtils.isEmpty(request.getFrontImg())){
            throw new KxgException("99999","首页图片不能为空");
        }
        if (CollectionUtils.isEmpty(request.getImgList())&&StringUtils.isEmpty(request.getFrontImg())){
            throw new KxgException("99999","9图不能为空或者视频连接不能都为空");
        }
        if (StringUtils.isEmpty(request.getName())){
            throw new KxgException("99999","商品名称不能为空");
        }
        if (null==request.getUserId()){
            throw new KxgException("99999","userId不能为空");
        }
        GoodsInfo goodsInfo=new GoodsInfo();
        BeanUtils.copyProperties(request,goodsInfo);
        if (!CollectionUtils.isEmpty(request.getImgList())){
            goodsInfo.setImgList(JsonUtils.convertObjectToJSON(request.getImgList()));
        }
        goodsDao.addGoods(goodsInfo);
        return new IntegerResult();
    }

    @Override
    public FindGoodsResponse findGoods(FindGoodsRequest request) {
        List<GoodsDto> goodsDtos = goodsDao.findGoodsInfo(request.getPageNumber(), request.getPageSize()).stream().map(new Function<GoodsInfo, GoodsDto>() {
            @Override
            public GoodsDto apply(GoodsInfo goodsInfo) {
                GoodsDto goodsDto = new GoodsDto();
                BeanUtils.copyProperties(goodsInfo, goodsDto);
                if (!StringUtils.isEmpty(goodsInfo.getImgList())) {
                    JsonUtils.toBean(goodsInfo.getImgList(), ArrayList.class);
                }
                return goodsDto;
            }
        }).collect(Collectors.toList());
        FindGoodsResponse response=new FindGoodsResponse();
        response.setGoodsDtos(goodsDtos);
        return response;
    }
}
