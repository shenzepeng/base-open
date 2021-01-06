package com.kxg.baseopen.provider.common;

import java.io.Serializable;

/**
 * 要写注释呀
 */
public class KxgResponse<T> {
    private String code;
    private String message;
    private T data;
    public KxgResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <K extends Object> KxgResponse error(K data){
        return create(ReturnCode.QUEST_FAIL, data);
    }
    public static <K extends Object> KxgResponse ok(K data){
        return create(ReturnCode.SUCCESS, data);
    }
    public static KxgResponse ok(){
        return create(ReturnCode.SUCCESS, null);
    }
    public static <K extends Object> KxgResponse create(ReturnCode returnCode, K data){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(), data);
    }
    public static KxgResponse create(ReturnCode returnCode){
        return new KxgResponse(returnCode.getCode(), returnCode.getMsg(),null);
    }
}
