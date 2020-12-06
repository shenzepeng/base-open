package com.kxg.baseopen.provider.exception;

/**
 * 要写注释呀
 */
public class KxgException extends RuntimeException{
    private String code;
    private String msg;

    public KxgException(String code, String msg){
        super(code + " : " + msg);
        this.code = code;
        this.msg = msg;
    }

}
