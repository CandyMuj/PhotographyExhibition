package com.cc.pic.api.intercept.interceptor;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.config.CacheKey;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.JwtUtil;
import com.cc.pic.api.utils.sys.utilsbean.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cc.pic.api.config.SecurityConstants.REQ_HEADER;

/**
 * @ProJectName APIServer
 * @FileName AuthInterceptor
 * @Description 接口请求时认证校验
 * @Author CandyMuj
 * @Date 2019/12/26 10:39
 * @Version 1.0
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;

            // 获取自定义注解
            Ann ann = h.getMethod().getDeclaredAnnotation(Ann.class);
            if (ann != null) {
                // 验证此接口是否需要鉴权,且token是否有效
                if (ann.au()) {
                    log.info("AUTH : true");

                    String token = request.getHeader(REQ_HEADER);
                    if (!redisUtil.hasKey(CacheKey.AUTH_TOKEN_USER + token)) {
                        log.error("AUTH : validation failed");
                        throw new AuthException("validation failed");
                    }

                    User user = JwtUtil.parse(token);
                    if (user == null || user.getUserId() <= 0) {
                        log.error("AUTH : validation failed");
                        throw new AuthException("validation failed");
                    } else {
                        log.info("AUTH : validation success");
                    }
                } else {
                    log.warn("AUTH : false");
                }
            } else {
                log.warn("AUTH : false");
            }
        }

        return true;
    }

}
