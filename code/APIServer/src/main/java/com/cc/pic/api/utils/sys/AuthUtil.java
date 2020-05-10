package com.cc.pic.api.utils.sys;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;

import static com.cc.pic.api.config.SecurityConstants.*;

/**
 * @ProJectName APIServer
 * @FileName AuthUtil
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/30 16:18
 * @Version 1.0
 */
@Slf4j
public class AuthUtil {

    // 用作缓存，不需要每次都计算;使用get获取，不设置为public，防止篡改
    private static String basic_token;

    static {
        basic_token = generateBasic();
    }


    public static String getToken(String authorization) {
        return StringUtils.substringAfter(authorization, TOKEN_SPLIT);
    }

    public static String getBasicToken() {
        return basic_token;
    }

    public static String getBasicToken(String authorization) {
        return StringUtils.substringAfter(authorization, AUTH_SPLIT);
    }

    /**
     * 生成基本的接口鉴权的密钥
     */
    private static String generateBasic() {
        try {
            byte[] b = (INTERFACE_AUTH_USERNAME + ":" + INTERFACE_AUTH_PASSWORD).getBytes(StandardCharsets.UTF_8);
            return new BASE64Encoder().encode(b);
        } catch (Exception e) {
            log.error("AuthUtil Exception...", e);
        }

        return null;
    }

}
