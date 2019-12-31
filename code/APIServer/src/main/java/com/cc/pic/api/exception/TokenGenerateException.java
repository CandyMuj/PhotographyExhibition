package com.cc.pic.api.exception;

/**
 * @ProJectName APIServer
 * @FileName TokenGenerateException
 * @Description token生成异常
 * @Author CandyMuj
 * @Date 2019/12/30 17:32
 * @Version 1.0
 */
public class TokenGenerateException extends RuntimeException {

    public TokenGenerateException(String msg, Throwable t) {
        super(msg, t);
    }

    public TokenGenerateException(String msg) {
        super(msg);
    }

}
