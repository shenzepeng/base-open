package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.WxFastRegisterMapper;
import com.kxg.baseopen.provider.pojo.WxFastRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 要写注释呀
 */
@Repository
public class WxFastRegisterDao {
    @Autowired
    private WxFastRegisterMapper wxFastRegisterMapper;

    public Integer addWxFastRegister(WxFastRegister wxFastRegister){
        return wxFastRegisterMapper.insertSelective(wxFastRegister);
    }
}
