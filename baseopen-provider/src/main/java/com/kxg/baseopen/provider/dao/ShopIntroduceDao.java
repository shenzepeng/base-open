package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.BusinessIntroduceMapper;
import com.kxg.baseopen.provider.pojo.BusinessIntroduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class ShopIntroduceDao {
    @Autowired
    private BusinessIntroduceMapper businessIntroduceMapper;

    public List<BusinessIntroduce> findShopIntroduce(Long userId){
        Example example=new Example(BusinessIntroduce.class);
        example.createCriteria()
                .andEqualTo("userId",userId);
        return businessIntroduceMapper.selectByExample(example);
    }

    public Integer addBusiness(BusinessIntroduce businessIntroduce){
        return businessIntroduceMapper.insertSelective(businessIntroduce);
    }
}
