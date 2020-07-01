package com.cc.pic.api.config.sys.c;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.exception.ResultException;
import com.cc.pic.api.pojo.sys.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

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
    public Result authException(AuthException e) {
        log.error("auth failed...", e);
        return new Result(NO_AUTH, "Authentication failed");
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(ResultException.class)
    public Result<Object> resultException(ResultException e) {
        return new Result<>(e.getCode(), e.getData(), e.getErrcode(), e.getMsg());
    }

    /**
     * 方法参数校验 绑定参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationException(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        return Result.Error(error != null ? error.getDefaultMessage() : "null");
    }

    /**
     * 接口参数校验异常处理
     */
    @ExceptionHandler(ValidationException.class)
    public Result validationException(ValidationException e) {
        String split = ": ";
        String msg = e.getMessage();
        if (StrUtil.isNotBlank(msg)) {
            String[] s = msg.split(split);
            StringBuilder msgstr = new StringBuilder();
            if (s.length > 1) {
                for (int i = 1; i < s.length; i++) {
                    msgstr.append(s[i]);
                    if (i < s.length - 1) {
                        msgstr.append(split);
                    }
                }

                msg = msgstr.toString();
            }
        } else {
            msg = "null";
        }

        return Result.Error(msg);
    }

    /**
     * 其他所有的异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        log.error("software running error...", e);

        String msg = e.getMessage();
        return StrUtil.isNotBlank(msg) ? Result.Error(msg) : Result.ErrorBusy();
    }

}
