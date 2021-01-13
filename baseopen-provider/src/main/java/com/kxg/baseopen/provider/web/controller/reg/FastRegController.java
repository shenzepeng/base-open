package com.kxg.baseopen.provider.web.controller.reg;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.FastRegService;
import com.kxg.baseopen.provider.web.request.AddLicenseRequest;
import com.kxg.baseopen.provider.web.response.IntegerResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("fastReg")
public class FastRegController {
    @Autowired
    private FastRegService fastRegService;
    /**
     * 添加营业执照
     */
    @ApiOperation("添加营业执照")
    @PostMapping("add/lisense")
    public SzpJsonResult<IntegerResult> addLisense(@RequestBody AddLicenseRequest request){
        return SzpJsonResult.ok(fastRegService.addLicense(request));
    }


}
