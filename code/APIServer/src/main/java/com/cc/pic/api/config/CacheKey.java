package com.cc.pic.api.config;

/**
 * @ProJectName APIServer
 * @FileName CacheKey
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/27 17:33
 * @Version 1.0
 */
public class CacheKey {

    // ****************** 关于用户token相关的key
    // key:token val：user
    public static final String AUTH_TOKEN_USER = "auth:token_user:";
    // key:userId val：token(若是多个则是一个列表)
    public static final String AUTH_USER_TOKEN = "auth:user_token:";

}
