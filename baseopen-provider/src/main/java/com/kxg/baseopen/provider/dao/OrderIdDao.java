package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.OrderIdMapper;
import com.kxg.baseopen.provider.mapper.OrderInfoMapper;
import com.kxg.baseopen.provider.pojo.OrderId;
import com.kxg.baseopen.provider.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class OrderIdDao {
    @Autowired
    private OrderIdMapper orderIdMapper;

    public List<OrderId> orderInfoList(String orderId){
        Example example=new Example(OrderId.class);
        example.createCriteria()
                .andEqualTo("orderId",orderId);
        return orderIdMapper.selectByExample(example);
    }

    public Integer addOrder(OrderId orderId){
        return orderIdMapper.insertSelective(orderId);
    }

}
