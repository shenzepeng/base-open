package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.UserDao;
import com.kxg.baseopen.provider.exception.KxgException;
import com.kxg.baseopen.provider.pojo.UserInfo;
import com.kxg.baseopen.provider.service.UserService;
import com.kxg.baseopen.provider.web.request.AddUserInfoRequest;
import com.kxg.baseopen.provider.web.request.FindUserPhoneAndOpenIdRequest;
import com.kxg.baseopen.provider.web.request.UpdateUserInfoRequest;
import com.kxg.baseopen.provider.web.response.FindUserPhoneAndOpenIdResponse;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import com.kxg.baseopen.provider.web.response.SmsLoginResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        //设置为代理
        userInfo.setStatus(2);
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
        FindUserPhoneAndOpenIdResponse response=new FindUserPhoneAndOpenIdResponse();
        if (CollectionUtils.isEmpty(userInfo)){
            return response;
        }
        BeanUtils.copyProperties(userInfo.get(0),response);
        response.setUserId(userInfo.get(0).getId());
        response.setUserExist(Boolean.TRUE);
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

    @Override
    public IntegerResult updateUserInfo(UpdateUserInfoRequest request) {
        if (StringUtils.isEmpty(request.getAppId())||StringUtils.isEmpty(request.getOpenId())){
            throw new KxgException("99999","请检查入参");
        }
        List<UserInfo> userInfo = userDao.findUserInfo(request.getAppId(), request.getOpenId());
        if (CollectionUtils.isEmpty(userInfo)){
            throw new KxgException("99999","当前用户不存在");
        }
        UserInfo info = userInfo.get(0);
        info.setPhoneNumber(request.getPhoneNumber());
        info.setNickName(request.getNickName());
        info.setImgUrl(request.getImgUrl());
        info.setInfoMsg(request.getInfoMsg());
        userDao.updateUserInfo(info);
        return new IntegerResult();
    }

    @Override
    public IntegerResult addUserInfo(AddUserInfoRequest request) {
        List<UserInfo> userInfo = userDao.findUserInfo(request.getAppId(), request.getOpenId());
        if (!CollectionUtils.isEmpty(userInfo)){
            return new IntegerResult();
        }
        UserInfo info=new UserInfo();
        info.setAppId(request.getAppId());
        info.setOpenId(request.getOpenId());
        userDao.addUserInfo(info);
        return new IntegerResult();
    }
}
