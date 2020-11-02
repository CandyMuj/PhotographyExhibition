package com.cc.pic.api.src.enumc;

/**
 * @ProjectName api
 * @FileName LogType
 * @Description 日志表-日志类型
 * @Author CandyMuj
 * @Date 2020/10/9 9:34
 * @Version 1.0
 */
public enum LogType {
    IFACEO(1, "外部接口调用"),
    IFACES(2, "系统接口调用"),
    SYSTEM(3, "系统内部操作"),
    ;

    private final Integer type;

    LogType(Integer type, String msg) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
