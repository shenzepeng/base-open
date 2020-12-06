package com.kxg.baseopen.provider.common;
import com.google.common.collect.Maps;

import java.util.Map;
/**
 * 要写注释呀
 */
public enum  ReturnCode {
    SUCCESS("00000", null),
    ARGUMENT_NOT_VALID("50008", "请求参数校验失败"),
    QUEST_FAIL("99999","请求失败");

    private String code;
    private String msg;
    private static final Map<String,ReturnCode> CODE_MAP = Maps.newHashMap();
    static{
        ReturnCode[] values = ReturnCode.values();
        for(ReturnCode returnCode : values){
            CODE_MAP.put(returnCode.code,returnCode);
        }
    }
    ReturnCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public String getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public static ReturnCode get(String code){
        return CODE_MAP.get(code);
    }
}
