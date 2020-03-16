package com.cc.pic.api.config;

import com.cc.pic.api.enumc.sys.TokenGenerateEnum;

/**
 * @ProJectName APIServer
 * @FileName SecurityConstants
 * @Description 关于安全 token 相关的一些配置
 * 注意 ！！！！
 * 此类是鉴权相关的配置，最好只做修改，且改该改的，不要新增和删除。。。
 * @Author CandyMuj
 * @Date 2019/12/24 18:03
 * @Version 1.0
 */
public class SecurityConstants {

    // jwt签名密钥
    public static final String JWT_SECRET = "CandyMuj";

    // token请求头
    public static final String REQ_HEADER = "Authorization";

    // token过期时间（天）
    public static final Integer EXPIRATION = 7;

    // token分割符 用于用户接口授权
    public static final String TOKEN_SPLIT = "Bearer ";

    // 接口鉴权分割符
    public static final String AUTH_SPLIT = "Basic ";

    // 接口鉴权：用户名
    public static final String INTERFACE_AUTH_USERNAME = "CandyMuj";

    // 接口鉴权：密码
    public static final String INTERFACE_AUTH_PASSWORD = "CandyMuj!123_";

    // TOKEN 生成策略
    public static final TokenGenerateEnum TOKEN_GENERATE_ENUM = TokenGenerateEnum.ONLY_ALIVE;

}
