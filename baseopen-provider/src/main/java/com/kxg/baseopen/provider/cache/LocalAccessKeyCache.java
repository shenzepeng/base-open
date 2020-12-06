package com.kxg.baseopen.provider.cache;

import com.kxg.baseopen.provider.dao.OpenWxAccessTokenDao;
import com.kxg.baseopen.provider.pojo.OpenWxAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 要写注释呀
 */
@Component
public class LocalAccessKeyCache extends BaseCache<String,Long> {

    @Override
    protected Optional<Long> loadData(String s) throws Exception {

        return Optional.empty();
    }
}
