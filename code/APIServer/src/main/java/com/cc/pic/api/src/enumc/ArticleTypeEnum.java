package com.cc.pic.api.src.enumc;

/**
 * @ProjectName api
 * @FileName ArticleTypeEnum
 * @Description
 * @Author CandyMuj
 * @Date 2020/5/15 16:10
 * @Version 1.0
 */
public enum ArticleTypeEnum {
    SYS(1, "系统文章 所有由管理员添加的文章"),
    ;

    private Integer type;

    ArticleTypeEnum(Integer type, String msg) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
