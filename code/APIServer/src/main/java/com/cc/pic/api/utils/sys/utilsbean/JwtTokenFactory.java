package com.cc.pic.api.utils.sys.utilsbean;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.config.CacheKey;
import com.cc.pic.api.enumc.sys.TokenGenerateEnum;
import com.cc.pic.api.exception.TokenGenerateException;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.JwtUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

import static com.cc.pic.api.config.SecurityConstants.EXPIRATION;
import static com.cc.pic.api.config.SecurityConstants.TOKEN_GENERATE_ENUM;

/**
 * @ProJectName APIServer
 * @FileName JwtTokenGenerate
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/30 17:16
 * @Version 1.0
 */
@Component
public class JwtTokenFactory {
    @Resource
    private RedisUtil redisUtil;


    public String generateToken(User user) {
        // 生成新的token 且之前的不失效
        if (TOKEN_GENERATE_ENUM.equals(TokenGenerateEnum.ONLY_ALIVE)) {
            String token = JwtUtil.create(user);

            Set<Object> tokenSet = getTokens(user.getUserId());
            // 剔除过期校验失败的token；实际就无效的token
            delInvalidFailed(tokenSet);
            tokenSet.add(token);
            saveToRedis(user, token, tokenSet);

            return token;
        }
        // 生成新的token 且之前的失效（即此用户永远只有一个唯一的token 且每次生成的都是新的）
        else if (TOKEN_GENERATE_ENUM.equals(TokenGenerateEnum.ONLY_DEATH)) {
            String token = JwtUtil.create(user);
            Set<Object> tokenSet = new HashSet<>();
            tokenSet.add(token);
            saveToRedis(user, token, tokenSet);

            return token;
        }
        // 如果含有未过期的token那么就用它，否则就生成新的（即此用户永远只有一个唯一的token 如果旧的token没过期那么每次获取的token都是相同的）
        else if (TOKEN_GENERATE_ENUM.equals(TokenGenerateEnum.OLD)) {
            Set<Object> tokenSet = getTokens(user.getUserId());
            // 剔除过期校验失败的token；实际就无效的token
            delInvalidFailed(tokenSet);

            if (tokenSet.size() > 0) {
                if (tokenSet.size() == 1) {
                    return tokenSet.iterator().next().toString();
                } else {
                    throw new TokenGenerateException("generate error has vaild token size more than 1 size:" + tokenSet.size());
                }
            } else {
                String token = JwtUtil.create(user);
                tokenSet.add(token);
                saveToRedis(user, token, tokenSet);

                return token;
            }
        } else {
            throw new TokenGenerateException("unsupported generate type");
        }
    }

    private Set<Object> getTokens(int userId) {
        Set<Object> tokenSet = redisUtil.sGet(CacheKey.AUTH_USER_TOKEN + userId);
        if (tokenSet == null) {
            tokenSet = new HashSet<>();
        }

        return tokenSet;
    }

    private void saveToRedis(User user, String token, Set<Object> tokenSet) {
        redisUtil.set(CacheKey.AUTH_TOKEN_USER + token, user, EXPIRATION * 86400);
        redisUtil.set(CacheKey.AUTH_USER_TOKEN + user.getUserId(), tokenSet);
    }

    /**
     * 剔除过期校验失败的token；实际就无效的token
     *
     * @param tokenSet
     */
    private void delInvalidFailed(Set<Object> tokenSet) {
        // 用另一个变量来循环，防止Remove后数组越界
        Set<Object> tempTokenSet = new HashSet<>(tokenSet);

        tempTokenSet.forEach(obj -> {
            String token = obj.toString();
            try {
                JwtUtil.parse(token);
            } catch (Exception e) {
                tokenSet.remove(obj);
            }
        });
    }


    /**
     * 从持久化处进行真实性验证
     * 验证token是否在redis中还存在（是否过期）;验证真实性
     *
     * @param token
     * @return 返回的对象不为null则表示有效
     */
    public User validateToken(String token) {
        try {
            if (StrUtil.isNotBlank(token) && hasToken(token)) {
                User user = JwtUtil.parse(token);
                if (user != null && user.getUserId() > 0) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 仅验证redis中是否有token（一般未过期就会，有效的才会有）
     *
     * @param token
     * @return
     */
    public boolean hasToken(String token) {
        return redisUtil.hasKey(CacheKey.AUTH_TOKEN_USER + token);
    }

}
