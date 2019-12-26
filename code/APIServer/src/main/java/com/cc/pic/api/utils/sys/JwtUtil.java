package com.cc.pic.api.utils.sys;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

import static com.cc.pic.api.config.SecurityConstants.EXPIRATION;
import static com.cc.pic.api.config.SecurityConstants.JWT_SECRET;

/**
 * @ProJectName APIServer
 * @FileName JwtTokenUtil
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/25 11:45
 * @Version 1.0
 */
@Slf4j
public class JwtUtil {

    public static String create(User user) {
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置过期时间
                .setExpiration(DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, EXPIRATION))
                // 设置签名秘钥
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes())

                /** token添加自定义属性 **/
                .claim("timestemp", System.currentTimeMillis())
                .claim("user", user);

        return jwtBuilder.compact();
    }

    public static User parse(String jwtToken) {
        try {
            if (StrUtil.isBlank(jwtToken)) return null;

            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET.getBytes())
                    .parseClaimsJws(jwtToken)
                    .getBody();

            return BeanUtil.toBean(claims.get("user", Map.class), User.class);
        } catch (Exception e) {
            throw new AuthException("Verification token Error...", e);
        }
    }

}
