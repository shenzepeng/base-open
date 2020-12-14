package com.kxg.baseopen.provider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 要写注释呀
 */
@AllArgsConstructor
public enum  WxActionEnums {
    ADD("add", (byte)0,"添加"),
    DELETE( "delete", (byte)1, "删除"),
    SET("set",  (byte)2,"覆盖"),
    GET("get",  (byte)3,"获取");


    @Getter
    private String action;


    @Getter
    private Byte code;

    @Getter
    private String name;

    public static WxActionEnums formatByCode(Byte code) {
        if (null == code) {
            return null;
        }
        switch (code) {
            case 0:
                return WxActionEnums.ADD;
            case 1:
                return WxActionEnums.DELETE;
            case 2:
                return WxActionEnums.SET;
            case 3:
                return WxActionEnums.GET;
            default:
                return null;
        }
    }

    public static List<Byte> toList() {
        List<Byte> list = new ArrayList();
        for (WxActionEnums wxActionEnums : WxActionEnums.values()) {
            list.add(wxActionEnums.getCode());
        }
        return list;
    }
}
