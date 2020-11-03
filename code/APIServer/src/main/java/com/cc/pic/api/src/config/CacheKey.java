package com.cc.pic.api.src.config;

import com.cc.pic.api.src.enumc.SmsEnum;

/**
 * @ProjectName api
 * @FileName CacheKey
 * @Description
 * @Author CandyMuj
 * @Date 2020/5/18 15:36
 * @Version 1.0
 */
public class CacheKey {

    // 字典缓存
    public static final String DICT_CACHE = "sys:dict:";
    public static final String DICT_CACHE_ALL = DICT_CACHE + "all";
    public static final String DICT_CACHE_CODE = DICT_CACHE + "code:";
    public static final String DICT_CACHE_TYPE = DICT_CACHE + "type:";
    public static final String DICT_CACHE_PID = DICT_CACHE + "pid:";
    public static final String DICT_CACHE_ID = DICT_CACHE + "id:";

    // 短信验证码缓存前缀
    private static final String SMS_CODE = "vercode:sms:";
    // 图形验证码缓存前缀
    private static final String IMG_CODE = "vercode:img:";
    // 系统配置
    private static final String SYS_CONFIG = "sys:config";


    public static String smsCode(String phone, SmsEnum smsEnum) {
        return SMS_CODE.concat(smsEnum.getKey()).concat(":").concat(phone);
    }

    public static String imgCode(String fingerprint) {
        return IMG_CODE.concat(fingerprint);
    }

    public static String sysConfig() {
        return SYS_CONFIG;
    }
}
