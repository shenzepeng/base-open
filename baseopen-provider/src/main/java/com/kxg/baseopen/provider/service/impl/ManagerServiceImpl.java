package com.kxg.baseopen.provider.service.impl;

import com.kxg.baseopen.provider.dao.NeedHandlerDao;
import com.kxg.baseopen.provider.dto.AllNeedHandlerDto;
import com.kxg.baseopen.provider.pojo.NeedHandler;
import com.kxg.baseopen.provider.service.ManagerService;
import com.kxg.baseopen.provider.web.request.FindNeedHandlerInfoRequest;
import com.kxg.baseopen.provider.web.response.FindAllNeedHandlerResponse;
import com.kxg.baseopen.provider.web.response.FindNeedHandlerInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Slf4j
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private NeedHandlerDao needHandlerDao;
    @Override
    public FindAllNeedHandlerResponse findAllNeedHandler() {
        List<AllNeedHandlerDto> allNeedHandlerDtos = needHandlerDao.findNeedHandler().stream().map(new Function<NeedHandler, AllNeedHandlerDto>() {
            @Override
            public AllNeedHandlerDto apply(NeedHandler needHandler) {
                AllNeedHandlerDto allNeedHandlerDto = new AllNeedHandlerDto();
                allNeedHandlerDto.setPhoneNumber(needHandler.getPhoneNumber());
                allNeedHandlerDto.setStatus(needHandler.getStatus());
                allNeedHandlerDto.setUserId(needHandler.getUserId());
                return allNeedHandlerDto;
            }
        }).collect(Collectors.toList());
        FindAllNeedHandlerResponse response=new FindAllNeedHandlerResponse();
        response.setAllNeedHandlerDtos(allNeedHandlerDtos);
        return response;
    }

    @Override
    public FindNeedHandlerInfoResponse findNeedHandlerInfo(FindNeedHandlerInfoRequest request) {
        NeedHandler needHandler = needHandlerDao.findByPrimaryKey(request.getId());
        FindNeedHandlerInfoResponse response=new FindNeedHandlerInfoResponse();
        AllNeedHandlerDto allNeedHandlerDto=new AllNeedHandlerDto();
        allNeedHandlerDto.setUserId(needHandler.getUserId());
        allNeedHandlerDto.setStatus(needHandler.getStatus());
        allNeedHandlerDto.setPhoneNumber(needHandler.getPhoneNumber());
        response.setDto(allNeedHandlerDto);
        return response;
    }
//    @Autowired
//    private
//    @Override
//    public FindAllNeedHandlerResponse findAllNeedHandler() {
//        return null;
//    }
}
