package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.OrderInfoMapper;
import com.kxg.baseopen.provider.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class OrderDao {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public Integer add(OrderInfo orderInfo){
        return orderInfoMapper.insertSelective(orderInfo);
    }

    public Integer update(OrderInfo orderInfo){
        return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }

    public List<OrderInfo> orderInfos(String orderId){
        Example example=new Example(OrderInfo.class);
        example.createCriteria()
                .andEqualTo("orderId",orderId);
        return orderInfoMapper.selectByExample(example);
    }
}
