package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.FastRegisterMapper;
import com.kxg.baseopen.provider.pojo.FastRegister;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class FastRegisterDao {
    @Autowired
    private FastRegisterMapper fastRegisterMapper;

    public Integer add(FastRegister fastRegister){
        return fastRegisterMapper.insertSelective(fastRegister);
    }

    public Integer update(FastRegister fastRegister){
        return fastRegisterMapper.updateByPrimaryKeySelective(fastRegister);
    }

    public List<FastRegister> findRegisterByCode(String code){
        if (StringUtils.isEmpty(code)){
            return new ArrayList<>();
        }
        Example example=new Example(FastRegister.class);
        example.createCriteria()
                .andEqualTo("code",code);
        return fastRegisterMapper.selectByExample(example);
    }
}
