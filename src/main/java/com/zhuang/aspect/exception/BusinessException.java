package com.zhuang.aspect.exception;

/**
 * 自定义业务异常
 */
public class BusinessException extends RuntimeException{

    public BusinessException(){
        super();
    }

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String msg,Throwable throwable){
        super(msg,throwable);
    }

    public BusinessException(Throwable throwable){
        super(throwable);
    }

}
