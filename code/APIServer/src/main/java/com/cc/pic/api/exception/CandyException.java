package com.cc.pic.api.exception;

/**
 * @ProJectName APIServer
 * @FileName AuthException
 * @Description 其他的异常自定义抛出类
 * @Author CandyMuj
 * @Date 2019/12/25 14:45
 * @Version 1.0
 */
public class CandyException extends RuntimeException {

    public CandyException(String msg, Throwable t) {
        super(msg, t);
    }

    public CandyException(String msg) {
        super(msg);
    }

}
