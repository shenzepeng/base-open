package com.kxg.baseopen.provider.web.controller;

import com.kxg.baseopen.provider.service.OpenWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@RestController
@RequestMapping("access")
public class AccessTokenController {
    @Autowired
    private OpenWxService openWxService;
    @GetMapping
    public String newAccessToken(){
        return openWxService.getAccessToken();
    }
}
