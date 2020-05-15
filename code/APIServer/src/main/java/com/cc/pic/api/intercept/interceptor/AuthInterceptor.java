package com.cc.pic.api.intercept.interceptor;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.AuthUtil;
import com.cc.pic.api.utils.sys.YmlConfig;
import com.cc.pic.api.utils.sys.bean.JwtTokenFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
    private JwtTokenFactory jwtTokenFactory;


    // 将接口鉴权uri的鉴权结果做一个缓存，不用每次都去循环配置内的排除鉴权项
    // {uri:{exclude:auth}}
    private static final Map<String, Boolean> INTERFACE_AUTH_RES = new HashMap<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(REQ_HEADER);

        // 进行接口鉴权 及 格式验证
        // 接口鉴权和方法内的鉴权不冲突，即使接口鉴权通过了，如果是一个方法，还是得看这个方法的认证是否通过才能最终确定是否有权访问
        if (!auth(request.getRequestURI(), authorization)) {
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
                    }
                } else {
                    log.warn("AUTH : false");
                }
            } else {
                log.warn("AUTH : false");
            }
        } else {
            // 由于此uri无对应的controller，所需需单独校验接口鉴权;因为存在一个问题，如果他使用token来校验，那么上面的校验会通过，但是他又不是一个方法，那么就会出现绕过鉴权的bug
            // 即使把上方的校验拿下来也不行的，因为他不传auth的token，依然会存在要过鉴权
            // 但是这里我对鉴权结果做的有缓存，多次调用也是无所谓的
            if (!exclude(request.getRequestURI())) {
                if (!AuthUtil.realAuthSplit(authorization) || !auth(request.getRequestURI(), authorization)) {
                    log.error("AUTH : validation failed, not a method and interface auth validation failed");
                    throw new AuthException("whithoutmethod interface auth validation failed");
                }
            }
        }

        log.info("AUTH : validation success");
        return true;
    }


    /**
     * 接口鉴权
     *
     * @return true: 成功
     */
    private boolean auth(String uri, String authorization) {
        boolean res = exclude(uri);

        if (!res) {
            boolean token = AuthUtil.realSplit(authorization);
            boolean auth = AuthUtil.realAuthSplit(authorization);
            if (!token && !auth) {
                res = false;
            } else if (auth && !AuthUtil.getAuthToken().equals(AuthUtil.getAuthToken(authorization))) {
                res = false;
            } else {
                res = true;
            }
        }

        return res;
    }

    /**
     * 验证是否在排除列表，若是直接鉴权成功
     */
    private boolean exclude(String uri) {
        Boolean res = INTERFACE_AUTH_RES.get(uri);

        if (res == null) {
            res = false;

            List<String> exclude = YmlConfig.getList("interface.auth.exclude");
            for (String str : exclude) {
                if (str.contains("*")) {
                    str = str.replace("*", ".*");
                    Pattern pattern = Pattern.compile(str);
                    res = pattern.matcher(uri).matches();
                } else {
                    res = str.equals(uri);
                }

                if (res) {
                    break;
                }
            }

            INTERFACE_AUTH_RES.put(uri, res);
        }

        return res;
    }

}
