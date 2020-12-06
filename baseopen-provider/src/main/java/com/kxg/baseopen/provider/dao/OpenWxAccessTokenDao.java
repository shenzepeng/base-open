package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.OpenWxAccessTokenMapper;
import com.kxg.baseopen.provider.pojo.OpenWxAccessToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * 要写注释呀
 */
@Repository
public class OpenWxAccessTokenDao {
    @Autowired
    private OpenWxAccessTokenMapper openWxAccessTokenMapper;

    public List<OpenWxAccessToken> getLastAccessToken() {
        Example example = new Example(OpenWxAccessToken.class);
        example.orderBy("id").desc();
        RowBounds rowBounds=new RowBounds(0,1);
        return openWxAccessTokenMapper.selectByExampleAndRowBounds(example,rowBounds);
    }


    public Integer addAccessToken(OpenWxAccessToken openWxAccessToken){
        return openWxAccessTokenMapper.insertSelective(openWxAccessToken);
    }
}
