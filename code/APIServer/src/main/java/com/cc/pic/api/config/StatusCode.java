package com.cc.pic.api.config;

/**
 * @ProJectName APIServer
 * @FileName ErrorCode
 * @Description 状态码 错误码等配置
 * @Author CandyMuj
 * @Date 2019/12/24 17:21
 * @Version 1.0
 */
public class StatusCode {

    // ***************** 错误码
    // 成功/正常
    public static final int SUCCESS = 1;
    public static final String SUCCESSS = "success";
    public static final int OK = 1;
    public static final String OKS = "ok";
    // 失败
    public static final int FAIL = 0;
    public static final String FAILS = "fail";
    // 错误/异常
    public static final int ERROR = -1;
    public static final String ERRORS = "error";


    // 未登录 未获取到token 无权访问
    public static final int NO_AUTH = 403;


}
