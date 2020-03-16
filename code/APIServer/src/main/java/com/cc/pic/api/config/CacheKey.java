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

    /**
     * 上方是关于系统或全局的配置 （若只需要框架，此处的配置不建议删除，建议只修改，视具体情况而定）
     */

    // ****************** 关于用户token相关的key
    // key:token val：user
    public static final String AUTH_TOKEN_USER = "auth:token_user:";
    // key:userId val：token(若是多个则是一个列表)
    public static final String AUTH_USER_TOKEN = "auth:user_token:";


    /**
     * 下方写关于自己业务的一些配置，避免建过多的重复文件，造成冗余 （若只需要框架，此处的配置可全部删除，可视具体情况保留需要的）
     */

}
