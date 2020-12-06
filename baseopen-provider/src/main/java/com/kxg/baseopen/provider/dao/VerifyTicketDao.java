package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.ComponentVerifyTicketMapper;
import com.kxg.baseopen.provider.pojo.ComponentVerifyTicket;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class VerifyTicketDao {
    @Autowired
    private ComponentVerifyTicketMapper componentVerifyTicketMapper;

    public List<ComponentVerifyTicket> findLast(){
        Example example=new Example(ComponentVerifyTicket.class);
        example.orderBy("id").desc();
        RowBounds rowBounds=new RowBounds(0,1);
        return componentVerifyTicketMapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    public Integer addNewComponent(ComponentVerifyTicket componentVerifyTicket){
        return componentVerifyTicketMapper.insertSelective(componentVerifyTicket);
    }
}
