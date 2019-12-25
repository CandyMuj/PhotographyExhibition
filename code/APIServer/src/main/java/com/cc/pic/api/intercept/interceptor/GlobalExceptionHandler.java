package com.cc.pic.api.intercept.interceptor;

import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cc.pic.api.enumc.StatusCode.NO_AUTH;


/**
 * @ProJectName APIServer
 * @FileName GlobalExceptionHandler
 * @Description 全局异常处理
 * @Author CandyMuj
 * @Date 2019/12/25 15:00
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 认证异常处理
     */
    @ExceptionHandler(AuthException.class)
    public Result authException(AuthException authException) {
        log.error("auth failed...");
        authException.printStackTrace();
        return new Result(NO_AUTH, "认证失败");
    }

    /**
     * 其他所有的异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception) {
        log.error("software running error...");
        exception.printStackTrace();
        return Result.ErrorBusy();
    }

}
