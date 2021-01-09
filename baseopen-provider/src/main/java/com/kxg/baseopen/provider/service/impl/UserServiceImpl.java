package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.UserDao;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.mapper.UserInfoMapper;
import com.kxg.baseopen.provider.pojo.UserInfo;
import com.kxg.baseopen.provider.service.UserService;
import com.kxg.baseopen.provider.web.request.FindUserPhoneAndOpenIdRequest;
import com.kxg.baseopen.provider.web.response.FindUserPhoneAndOpenIdResponse;
import com.kxg.baseopen.provider.web.response.SmsLoginResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 要写注释呀
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void findUserInfo(String phoneNumber) {
        List<UserInfo> userInfoList = userDao.findUserInfo(phoneNumber);
        if (!CollectionUtils.isEmpty(userInfoList)){
            return;
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setPhoneNumber(phoneNumber);
        userDao.addUserInfo(userInfo);
    }

    @Override
    public FindUserPhoneAndOpenIdResponse findUserInfo(FindUserPhoneAndOpenIdRequest request) {
        if (StringUtils.isEmpty(request.getPhoneNumber())){
            if (StringUtils.isEmpty(request.getAppId())||StringUtils.isEmpty(request.getOpenId())){
                throw new KxgException("99999","请检查入参");
            }
        }
        List<UserInfo> userInfo = userDao.findUserInfo(request.getAppId(), request.getOpenId(), request.getPhoneNumber());
        if (CollectionUtils.isEmpty(userInfo)){
            throw new KxgException("99999","没有当前的用户信息");
        }
        FindUserPhoneAndOpenIdResponse response=new FindUserPhoneAndOpenIdResponse();
        BeanUtils.copyProperties(userInfo.get(0),response);
        response.setUserId(userInfo.get(0).getId());
        return response;
    }

    @Override
    public SmsLoginResponse login(String phoneNumber) {
        //防止没有这个人
        findUserInfo(phoneNumber);
        List<UserInfo> userInfo = userDao.findUserInfo(phoneNumber);
        UserInfo info = userInfo.get(0);
        SmsLoginResponse response=new SmsLoginResponse();
        response.setAppId(info.getAppId());
        response.setOpenId(info.getOpenId());
        response.setStatus(info.getStatus());
        response.setUserId(info.getId());
        if (!StringUtils.isEmpty(info.getAppId())){
            response.setHadCreateApp(Boolean.TRUE);
        }
        return response;
    }
}
