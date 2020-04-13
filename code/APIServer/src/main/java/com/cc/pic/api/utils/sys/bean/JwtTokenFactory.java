package com.cc.pic.api.utils.sys.bean;

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

            if (saveToRedis(user, token, tokenSet)) {
                return token;
            }
        }
        // 生成新的token 且之前的失效（即此用户永远只有一个唯一的token 且每次生成的都是新的,有且仅有一个有效token）
        else if (TOKEN_GENERATE_ENUM.equals(TokenGenerateEnum.ONLY_DEATH)) {
            String token = JwtUtil.create(user);
            Set<Object> tokenSet = new HashSet<>();
            tokenSet.add(token);

            if (saveToRedis(user, token, tokenSet)) {
                return token;
            }
        }
        // 如果含有未过期的token那么就用它，否则就生成新的（即此用户永远只有一个唯一的token 如果旧的token没过期那么每次获取的token都是相同的,有且仅有一个有效token）
        else if (TOKEN_GENERATE_ENUM.equals(TokenGenerateEnum.OLD)) {
            Set<Object> tokenSet = getTokens(user.getUserId());
            // 剔除过期校验失败的token；实际就无效的token
            delInvalidFailed(tokenSet);

            if (tokenSet.size() > 0) {
                if (tokenSet.size() == 1) {
                    // 这里直接获取列表的第一个，第一个是最后添加的，先进后出，后进先出，所以获取的都是最新的
                    // 其实新旧也无所谓，哪怕你这次获取的最新的，但是新的如果过期了，旧的没过期，但是如果这期间数据更新了，用到了旧的token，旧数据可能就有问题
                    // 但又不太可能，只要按规定使用user中的对象：尽量只使用id，因为这个永远不可变
                    // 还有一点如果不在这期间不修改过期时间，那么就不会出现这些问题，因为新的都过期了，旧的肯定早就过期了
                    // 还有一点，如果真的修改了时间，那么每次修改时务必同步把缓存清完，就不会出现问题，但是会影响已登录的token会全部失效，所以尽量修改过期时间即可，其实就算修改这个影响也小，重新登录多正常的事，没有固定的时间，我也经常遇到这些问题，有些网站重新登录的时间就不同，很正常的
                    return tokenSet.iterator().next().toString();
                } else {
                    throw new TokenGenerateException("generate error has vaild token size more than 1 size:" + tokenSet.size());
                }
            } else {
                String token = JwtUtil.create(user);
                tokenSet.add(token);

                if (saveToRedis(user, token, tokenSet)) {
                    return token;
                }
            }
        } else {
            throw new TokenGenerateException("unsupported generate type");
        }

        return null;
    }

    private Set<Object> getTokens(int userId) {
        Set<Object> tokenSet = redisUtil.sGet(CacheKey.AUTH_USER_TOKEN + userId);
        if (tokenSet == null) {
            tokenSet = new HashSet<>();
        }

        return tokenSet;
    }

    private boolean saveToRedis(User user, String token, Set<Object> tokenSet) {
        return redisUtil.set(CacheKey.AUTH_TOKEN_USER + token, user, EXPIRATION * 86400) &&
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
