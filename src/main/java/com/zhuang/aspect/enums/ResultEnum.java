package com.zhuang.aspect.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0,"请求成功"),
    ERROR(-1,"请求错误");

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
