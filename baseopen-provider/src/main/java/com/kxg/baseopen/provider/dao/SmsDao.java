package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.SmsInfoMapper;
import com.kxg.baseopen.provider.pojo.SmsInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class SmsDao {
    @Autowired
    private SmsInfoMapper smsInfoMapper;

    public List<SmsInfo> findSmsInfo(String phoneNumber){
        Example example=new Example(SmsInfo.class);
        example.createCriteria()
                .andEqualTo("phoneNumber",phoneNumber);
        example.orderBy("id").desc();
        RowBounds rowBounds=new RowBounds(0,1);
        return smsInfoMapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    public Integer addSms(SmsInfo smsInfo){
        return smsInfoMapper.insertSelective(smsInfo);
    }

    public Integer update(SmsInfo smsInfo){
        return smsInfoMapper.updateByPrimaryKeySelective(smsInfo);
    }
}
