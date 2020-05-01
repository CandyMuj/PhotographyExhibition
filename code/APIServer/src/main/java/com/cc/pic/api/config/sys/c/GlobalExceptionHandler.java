package com.cc.pic.api.config.sys.c;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.cc.pic.api.config.StatusCode.NO_AUTH;


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
        log.error("auth failed...", authException);
        return new Result(NO_AUTH, "Authentication failed");
    }

    /**
     * 其他所有的异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception exception) {
        log.error("software running error...", exception);

        String msg = exception.getMessage();
        return StrUtil.isNotBlank(msg) ? Result.Error(msg) : Result.ErrorBusy();
    }

}
