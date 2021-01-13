package com.kxg.baseopen.provider.web.controller.manager;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.ManagerService;
import com.kxg.baseopen.provider.web.request.FindNeedHandlerInfoRequest;
import com.kxg.baseopen.provider.web.response.FindAllNeedHandlerResponse;
import com.kxg.baseopen.provider.web.response.FindNeedHandlerInfoResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户管理
 */
@RestController
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @ApiOperation("待处理列表")
    @PostMapping("wait/handler")
    public SzpJsonResult<FindAllNeedHandlerResponse> getNeedList(){
        return SzpJsonResult.ok(managerService.findAllNeedHandler());
    }
    @ApiOperation("通过处理id查询详情")
    @PostMapping("wait/handler/by/id")
    public SzpJsonResult<FindNeedHandlerInfoResponse> findHandlerById(@RequestBody FindNeedHandlerInfoRequest request){
        return SzpJsonResult.ok(managerService.findNeedHandlerInfo(request));
    }
}
