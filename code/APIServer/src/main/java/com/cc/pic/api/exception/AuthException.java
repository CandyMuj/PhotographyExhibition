package com.cc.pic.api.exception;

/**
 * @ProJectName APIServer
 * @FileName AuthException
 * @Description 认证登录token验证失败的异常
 * @Author CandyMuj
 * @Date 2019/12/25 14:45
 * @Version 1.0
 */
public class AuthException extends RuntimeException {

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg) {
        super(msg);
    }

}
