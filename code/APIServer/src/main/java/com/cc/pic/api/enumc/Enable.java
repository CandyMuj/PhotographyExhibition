package com.cc.pic.api.enumc;

/**
 * @ProJectName AmericanDelicacy
 * @FileName Enable
 * @Description
 * @Author CandyMuj
 * @Date 2020/4/9 13:09
 * @Version 1.0
 */
public enum Enable {
    DISENABLE(0, "否 禁用 审核中"),
    ENABLE(1, "是 启用 通过"),
    DELETE(2, "删除 拒绝");


    private int code;

    public Integer getCode() {
        return this.code;
    }

    Enable(int code, String name) {
        this.code = code;
    }
}
