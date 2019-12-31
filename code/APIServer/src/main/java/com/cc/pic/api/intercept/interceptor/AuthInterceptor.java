package com.cc.pic.api.intercept.interceptor;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.AuthUtil;
import com.cc.pic.api.utils.sys.bean.JwtTokenFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.cc.pic.api.config.SecurityConstants.REQ_HEADER;
import static com.cc.pic.api.config.SecurityConstants.TOKEN_SPLIT;

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
    private JwtTokenFactory jwtTokenFactory;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(REQ_HEADER);

        // 进行接口鉴权
        if (StrUtil.isBlank(authorization) || (!authorization.contains(TOKEN_SPLIT) && !AuthUtil.getBasicToken().equals(AuthUtil.getBasicToken(authorization)))) {
            log.error("AUTH : interface auth validation failed");
            throw new AuthException("interface auth validation failed");
        }


        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;

            // 获取自定义注解
            Ann ann = h.getMethod().getDeclaredAnnotation(Ann.class);
            if (ann != null) {
                // 验证此接口是否需要鉴权,且token是否有效
                if (ann.au()) {
                    log.info("AUTH : true");

                    User user = jwtTokenFactory.validateToken(AuthUtil.getToken(authorization));
                    if (user == null) {
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
