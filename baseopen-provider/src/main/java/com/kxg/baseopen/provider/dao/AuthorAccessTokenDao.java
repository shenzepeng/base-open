package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.AuthorizerTokenMapper;
import com.kxg.baseopen.provider.pojo.AuthorizerToken;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 小程序 权限管理acesstoken dao
 */
@Repository
public class AuthorAccessTokenDao {
    @Autowired
    private AuthorizerTokenMapper authorizerTokenMapper;

    public Integer addAuthorToken(AuthorizerToken authorizerToken){
        return authorizerTokenMapper.insertSelective(authorizerToken);
    }

    public List<AuthorizerToken> findLastAuthorToken(String appId){
        if (StringUtils.isEmpty(appId)){
            return new ArrayList<>();
        }
        Example example=new Example(AuthorizerToken.class);
        example.createCriteria()
                .andEqualTo("authorizerAppid",appId);
        example.orderBy("id").desc();
        RowBounds rowBounds=new RowBounds(0,1);
        return authorizerTokenMapper.selectByExampleAndRowBounds(example,rowBounds);
    }
}
