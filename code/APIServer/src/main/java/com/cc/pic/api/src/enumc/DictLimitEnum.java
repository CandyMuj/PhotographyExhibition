package com.cc.pic.api.src.enumc;

/**
 * @ProjectName api
 * @FileName DictLimitEnum
 * @Description
 * @Author CandyMuj
 * @Date 2020/5/15 15:53
 * @Version 1.0
 */
public enum DictLimitEnum {
    ADW(1, "增删改 无限制"),
    R(2, "只读（不可增删改）"),
    W(4, "改（可改 不可增删）"),
    DW(8, "改（可删改 不可增）"),
    ;

    private Integer type;

    DictLimitEnum(Integer type, String msg) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static DictLimitEnum val(Integer type) {
        if (type != null) {
            for (DictLimitEnum e : DictLimitEnum.values()) {
                if (e.getType().equals(type)) {
                    return e;
                }
            }
        }

        return null;
    }
}
