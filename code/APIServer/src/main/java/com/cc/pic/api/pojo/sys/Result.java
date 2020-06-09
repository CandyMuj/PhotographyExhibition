package com.cc.pic.api.pojo.sys;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.utils.DB;
import lombok.extern.slf4j.Slf4j;

import static com.cc.pic.api.config.StatusCode.FAIL;
import static com.cc.pic.api.config.StatusCode.SUCCESS;

/**
 * @ProJectName APIServer
 * @FileName Result
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/25 11:49
 * @Version 1.0
 */
@Slf4j
public class Result<T> {
    public int code;
    public Integer curPage;
    public Long totalCount;
    public Integer pageSize;
    public String msg;
    public T data;

    private Result() {
    }

    public Result(T data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(T data, String msg) {
        this(SUCCESS, data, msg);
    }

    public Result(int code, String msg) {
        this(code, null, msg);
    }

    public Result(Throwable e) {
        this(FAIL, null, e.getMessage());
    }

    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;

        printLog();
    }


    private void printLog() {
        if (StrUtil.isNotBlank(this.msg)) {
            log.info("Result Msg ===> {}", this.msg);
        }
    }


    public static Result OK() {
        return new Result(SUCCESS);
    }

    public static <T> Result OK(T data) {
        return new Result<>(data);
    }

    public static Result OK(String msg) {
        return new Result(SUCCESS, msg);
    }

    public static Result Error() {
        return Error("系统错误");
    }

    public static Result ErrorBusy() {
        return Error("服务器忙，请稍后再试...");
    }

    public static Result Error(String msg) {
        return Error(null, msg);
    }

    public static <T> Result Error(T data, String msg) {
        DB.setRollbackOnly();
        // 另一种spring提供的抛出异常的方法，用来更方便的抛出异常，使事务可以捕获，执行回滚
        // 但是在我这个框架里面不能用这种方式，此框架无法做到统一处理，因为在aop中也进行了error的返回那么就是死循环了（不停地进这个方法，不停地向上抛异常）
        // 如果用这种抛异常的方式，那么去掉方法体中的try catch catch (Exception e) { throw e;}继续向上抛，目的是让spring事务捕获这个异常
        // Assert.isTrue(false, msg);
        // throw new RuntimeException(msg);
        return new Result(FAIL, data, msg);
    }

}
