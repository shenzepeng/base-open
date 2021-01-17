package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.UserInfoMapper;
import com.kxg.baseopen.provider.pojo.UserInfo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户表
 */
@Repository
public class UserDao {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> findUserInfo(String phoneNumber){
        Example example=new Example(UserInfo.class);
        example.createCriteria()
                .andEqualTo("phoneNumber",phoneNumber);
        return userInfoMapper.selectByExample(example);
    }
    public Integer addUserInfo(UserInfo userInfo){
        return userInfoMapper.insertSelective(userInfo);
    }

    public List<UserInfo> findUserInfo(String appId,String openId,String phoneNumber){
        Example example=new Example(UserInfo.class);
        if (StringUtils.isEmpty(phoneNumber)) {
            example.createCriteria()
                    .andEqualTo("appId", appId)
                    .andEqualTo("openId", openId);
        }else {
            example.createCriteria()
                    .andEqualTo("appId", appId)
                    .andEqualTo("phoneNumber",phoneNumber);
        }
        return userInfoMapper.selectByExample(example);
    }

    public Integer updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    public List<UserInfo> findUserInfo(String appId,String openId){
        Example example=new Example(UserInfo.class);
        example.createCriteria()
                .andEqualTo("appId",appId)
                .andEqualTo("openId",openId);
        return userInfoMapper.selectByExample(example);
    }
}
