package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.CallBackCodeMapper;
import com.kxg.baseopen.provider.pojo.CallBackCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 要写注释呀
 */
@Repository
public class CallBackCodeDao {
    @Autowired
    private CallBackCodeMapper callBackCodeMapper;

    public Integer addCallBackCode(CallBackCode callBackCode){
        return callBackCodeMapper.insertSelective(callBackCode);
    }
}
