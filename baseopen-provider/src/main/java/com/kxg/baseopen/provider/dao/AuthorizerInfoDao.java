package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.AuthorizerInfoMapper;
import com.kxg.baseopen.provider.pojo.AuthorizerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 小程序
 * refresh token dao
 */
@Repository
public class AuthorizerInfoDao {
    @Autowired
    private AuthorizerInfoMapper authorizerInfoMapper;

    public Integer add(AuthorizerInfo authorizerInfo){
        return authorizerInfoMapper.insertSelective(authorizerInfo);
    }

    public List<AuthorizerInfo> findLastAppIdInfo(String appId){
        if (StringUtils.isEmpty(appId)){
            return new ArrayList<>();
        }
        Example example=new Example(AuthorizerInfo.class);
        example.createCriteria()
                .andEqualTo("authorizerAppid",appId);
        example.orderBy("id").desc();
        return authorizerInfoMapper.selectByExample(example);
    }
}
