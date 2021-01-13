package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.NeedHandlerMapper;
import com.kxg.baseopen.provider.pojo.NeedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 需要处理的逻辑
 */
@Repository
public class NeedHandlerDao {
    @Autowired
    private NeedHandlerMapper needHandlerMapper;

    public List<NeedHandler> findNeedHandler(){
        Example example=new Example(NeedHandler.class);
        example.createCriteria()
                .andNotEqualTo("status",4);
        return needHandlerMapper.selectByExample(example);
    }

    public Integer addNeedHandler(NeedHandler needHandler){
        return needHandlerMapper.insertSelective(needHandler);
    }

    public Integer updateNeedHandler(NeedHandler needHandler){
        return needHandlerMapper.updateByPrimaryKeySelective(needHandler);
    }

    public NeedHandler findByPrimaryKey(Long id){
        return needHandlerMapper.selectByPrimaryKey(id);
    }
}
