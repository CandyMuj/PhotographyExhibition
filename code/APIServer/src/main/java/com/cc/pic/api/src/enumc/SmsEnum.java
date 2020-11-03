package com.cc.pic.api.src.enumc;

/**
 * @ProjectName api
 * @FileName SmsEnum
 * @Description 验证码类型，及其对应的redis key
 * @Author CandyMuj
 * @Date 2020/10/10 11:39
 * @Version 1.0
 */
public enum SmsEnum {
    ,
    ;

    private final Integer type;
    private final String key;


    SmsEnum(Integer type, String key, String msg) {
        this.type = type;
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public static SmsEnum val(Integer type) {
        if (type != null) {
            for (SmsEnum o : SmsEnum.values()) {
                if (o.getType().equals(type)) {
                    return o;
                }
            }
        }

        return null;
    }

}
