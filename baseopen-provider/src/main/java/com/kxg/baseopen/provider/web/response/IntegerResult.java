package com.kxg.baseopen.provider.web.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 要写注释呀
 */
@Data
public class IntegerResult implements Serializable {
    private Integer  end;
    public IntegerResult(){
        this.end=1;
    }
    public void setEnd(){
        this.end=0;
    }
}
