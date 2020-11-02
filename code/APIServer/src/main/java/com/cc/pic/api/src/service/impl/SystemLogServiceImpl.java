package com.cc.pic.api.src.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.LogType;
import com.cc.pic.api.src.mapper.SystemLogMapper;
import com.cc.pic.api.src.pojo.SystemLog;
import com.cc.pic.api.src.pojo.vo.SystemLogVo;
import com.cc.pic.api.src.service.ISystemLogService;
import com.cc.pic.api.utils.DB;
import com.cc.pic.api.utils.IpUtil;
import com.cc.pic.api.utils.sys.YmlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ProjectName LaiDianPay
 * @FileName SystemLogServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/09/30 14:58
 * @Version 1.0
 */
@Slf4j
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements ISystemLogService {


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
    @Override
    public void ins(LogType logType, Long userAccountId, String describe, String restUrl, String restParam, HttpServletRequest request, String oldParam, Result<?> result) {
        SystemLog systemLog = new SystemLog();
        systemLog.setLogType(logType.getType());
        systemLog.setUserAccountId(userAccountId);
        systemLog.setDescribe(describe);
        systemLog.setOldParam(oldParam);
        systemLog.setAddTime(new Date());

        if (request == null) {
            systemLog.setRestUrl(restUrl);
            systemLog.setRestParam(restParam);
        } else {
            systemLog.setIp(IpUtil.getRealIP(request));
            systemLog.setRestUrl(request.getRequestURI());
            Map<String, String[]> params = request.getParameterMap();
            if (params != null) {
                params = new HashMap<>(params);
                // 移除sign字段
                params.put(YmlConfig.getString("src.sign.field"), null);
                // 移除随机字符
                params.put(YmlConfig.getString("src.sign.nonce"), null);
                // 移除时间戳
                params.put("timespan", null);
                // 把密码删掉，因为传参是明文的不能存
                if (params.get("password") != null) {
                    params.put("password", new String[]{"******"});
                }
                if (params.get("oldpwd") != null) {
                    params.put("oldpwd", new String[]{"******"});
                }
                if (params.get("newpwd") != null) {
                    params.put("newpwd", new String[]{"******"});
                }

                systemLog.setRestParam(JSONObject.toJSONString(params));
            }
        }
        if (result != null) {
            Result<?> result1 = new Result<>((Object) null);
            result1.code = result.code;
            result1.curPage = result.curPage;
            result1.totalCount = result.totalCount;
            result1.pageSize = result.pageSize;
            result1.errcode = result.errcode;
            result1.msg = result.msg;
            systemLog.setResult(JSONObject.toJSONString(result1));
        }

        if (!systemLog.insert()) {
            log.error("添加日志失败");
        }
    }

    @Override
    public void ins(LogType logType, String describe, String restUrl, String restParam, String oldParam, Result<?> result) {
        this.ins(logType, null, describe, restUrl, restParam, null, oldParam, result);
    }

    @Override
    public void ins(LogType logType, String describe, String restUrl, String restParam, Result<?> result) {
        this.ins(logType, describe, restUrl, restParam, null, result);
    }

    @Override
    public void ins(LogType logType, String describe, Result<?> result) {
        this.ins(logType, describe, (String) null, (String) null, result);
    }


    @Override
    public void ins(LogType logType, Long userAccountId, String describe, HttpServletRequest request, String oldParam, Result<?> result) {
        this.ins(logType, userAccountId, describe, null, null, request, oldParam, result);
    }

    @Override
    public void ins(LogType logType, Long userAccountId, String describe, HttpServletRequest request, Result<?> result) {
        this.ins(logType, userAccountId, describe, request, null, result);
    }

    @Override
    public void ins(LogType logType, String describe, HttpServletRequest request, String oldParam, Result<?> result) {
        this.ins(logType, null, describe, request, oldParam, result);
    }

    @Override
    public void ins(LogType logType, String describe, HttpServletRequest request, Result<?> result) {
        this.ins(logType, describe, request, null, result);
    }


    @Override
    public void add(LogType logType, Long userAccountId, String describe, String restUrl, String restParam, String oldParam) {
        this.ins(logType, userAccountId, describe, restUrl, restParam, null, oldParam, null);
    }

    @Override
    public void add(LogType logType, String describe, String restUrl, String restParam, String oldParam) {
        this.add(logType, null, describe, restUrl, restParam, oldParam);
    }

    @Override
    public void add(LogType logType, String describe, String restUrl, String restParam) {
        this.add(logType, describe, restUrl, restParam, null);
    }

    @Override
    public void add(LogType logType, String describe) {
        this.add(logType, describe, (String) null, (String) null);
    }

    @Override
    public void add(LogType logType, Long userAccountId, String describe, HttpServletRequest request, String oldParam) {
        this.ins(logType, userAccountId, describe, null, null, request, oldParam, null);
    }

    @Override
    public void add(LogType logType, Long userAccountId, String describe, HttpServletRequest request) {
        this.add(logType, userAccountId, describe, request, null);
    }

    @Override
    public void add(LogType logType, String describe, HttpServletRequest request, String oldParam) {
        this.add(logType, null, describe, request, oldParam);
    }

    @Override
    public void add(LogType logType, String describe, HttpServletRequest request) {
        this.add(logType, describe, request, null);
    }

    /**
     * 系统日志查询
     */
    @Override
    public Result<List<SystemLogVo>> list(SystemLogVo systemLogVo) {
        return DB.getPageRes(baseMapper.logList(systemLogVo));
    }

}
