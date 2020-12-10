package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.FastRegisterDao;
import com.kxg.baseopen.provider.pojo.FastRegister;
import com.kxg.baseopen.provider.service.FastRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class FastRegisterServiceImpl implements FastRegisterService {
    @Autowired
    private FastRegisterDao fastRegisterDao;
    @Override
    public void addFastRegister(FastRegister register) {
        fastRegisterDao.add(register);
    }

    @Override
    public void updateFastRegister(FastRegister fastRegister) {
        fastRegisterDao.update(fastRegister);
    }

    @Override
    public List<FastRegister> findFastRegister(String code) {
        return fastRegisterDao.findRegisterByCode(code);

    }


}
