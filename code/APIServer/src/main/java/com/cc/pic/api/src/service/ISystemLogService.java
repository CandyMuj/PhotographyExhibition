package com.cc.pic.api.src.service;

import com.baomidou.mybatisplus.service.IService;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.LogType;
import com.cc.pic.api.src.pojo.SystemLog;
import com.cc.pic.api.src.pojo.vo.SystemLogVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName LaiDianPay
 * @FileName ISystemLogService
 * @Description
 * @Author CandyMuj
 * @Date 2020/09/30 14:58
 * @Version 1.0
 */
public interface ISystemLogService extends IService<SystemLog> {

    /**
     * 添加日志
     *
     * @param logType       日志类型，枚举值
     * @param userAccountId 用户ID，仅在调用系统接口时可能存在
     * @param describe      操作描述
     * @param restUrl       接口地址
     * @param restParam     接口参数
     * @param oldParam      如果是修改，那么这是修改前的数据
     */
    void ins(LogType logType, Long userAccountId, String describe, String restUrl, String restParam, HttpServletRequest request, String oldParam, Result<?> result);

    void ins(LogType logType, String describe, String restUrl, String restParam, String oldParam, Result<?> result);

    void ins(LogType logType, String describe, String restUrl, String restParam, Result<?> result);

    void ins(LogType logType, String describe, Result<?> result);


    void ins(LogType logType, Long userAccountId, String describe, HttpServletRequest request, String oldParam, Result<?> result);

    void ins(LogType logType, Long userAccountId, String describe, HttpServletRequest request, Result<?> result);

    void ins(LogType logType, String describe, HttpServletRequest request, String oldParam, Result<?> result);

    void ins(LogType logType, String describe, HttpServletRequest request, Result<?> result);


    void add(LogType logType, Long userAccountId, String describe, String restUrl, String restParam, String oldParam);

    void add(LogType logType, String describe, String restUrl, String restParam, String oldParam);

    void add(LogType logType, String describe, String restUrl, String restParam);

    void add(LogType logType, String describe);


    void add(LogType logType, Long userAccountId, String describe, HttpServletRequest request, String oldParam);

    void add(LogType logType, Long userAccountId, String describe, HttpServletRequest request);

    void add(LogType logType, String describe, HttpServletRequest request, String oldParam);

    void add(LogType logType, String describe, HttpServletRequest request);

    Result<List<SystemLogVo>> list(SystemLogVo systemLogVo);
}
