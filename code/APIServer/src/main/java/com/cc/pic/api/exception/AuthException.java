package com.cc.pic.api.exception;

import com.cc.pic.api.config.StatusCode;

/**
 * @ProJectName APIServer
 * @FileName AuthException
 * @Description 认证登录token验证失败的异常
 * @Author CandyMuj
 * @Date 2019/12/25 14:45
 * @Version 1.0
 */
public class AuthException extends RuntimeException {
    private Integer resCode = StatusCode.NO_AUTH;


    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg) {
        super(msg);
    }

    public AuthException(String msg, Integer resCode) {
        super(msg);
        if (resCode != null) {
            this.resCode = resCode;
        }
    }

    public int getResCode() {
        return resCode;
    }
}
