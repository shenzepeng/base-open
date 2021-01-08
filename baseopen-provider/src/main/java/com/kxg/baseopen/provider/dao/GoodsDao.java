package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.GoodsInfoMapper;
import com.kxg.baseopen.provider.pojo.GoodsInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class GoodsDao {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    public List<GoodsInfo> findGoodsInfo(int  pageNumber,int pageSize){
        int offset = (pageNumber - 1) * pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        Example example=new Example(GoodsInfo.class);
        example.orderBy("id").desc();
        return goodsInfoMapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    public int getGoodsCount(){
        Example example=new Example(GoodsInfo.class);
        return goodsInfoMapper.selectCountByExample(example);
    }

    public int addGoods(GoodsInfo goodsInfo){
        return goodsInfoMapper.insertSelective(goodsInfo);
    }
}
