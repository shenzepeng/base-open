package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.AppIdFunMapper;
import com.kxg.baseopen.provider.pojo.AppIdFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class AppIdFunDao {
    @Autowired
    private AppIdFunMapper appIdFunMapper;

    public Integer addAppId(AppIdFun appIdFun){
        return appIdFunMapper.insertSelective(appIdFun);
    }

    public List<AppIdFun> findFunByAppId(String appId){
        Example example=new Example(AppIdFun.class);
        example.createCriteria()
                .andEqualTo("appId",appId);
        return appIdFunMapper.selectByExample(example);
    }

}
