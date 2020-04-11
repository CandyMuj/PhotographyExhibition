package com.cc.pic.api.pojo.sys;

import com.cc.pic.api.utils.DB;

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
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Throwable e) {
        this.code = FAIL;
        this.msg = e.getMessage();
    }


    public static Result OK() {
        return new Result(SUCCESS);
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
        DB.setRollbackOnly();
        return new Result(FAIL, msg);
    }

}
