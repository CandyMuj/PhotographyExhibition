package com.cc.pic.api.config.sys.c;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.config.SecurityConstants;
import com.cc.pic.api.exception.AuthException;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.AuthUtil;
import com.cc.pic.api.utils.sys.bean.JwtTokenFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

import static com.cc.pic.api.config.SecurityConstants.REQ_HEADER;

/**
 * @ProJectName APIServer
 * @FileName TokenArgumentResolver
 * @Description Token转化为User对象
 * @Author CandyMuj
 * @Date 2019/12/24 17:28
 * @Version 1.0
 */
@Slf4j
@Component
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private JwtTokenFactory jwtTokenFactory;


    /**
     * 1. 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(User.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory
    ) {
        String authorization = nativeWebRequest.getHeader(REQ_HEADER);

        // 如果是接口鉴权，那么直接返回空，后方就不做解析，否则会报错，因为token是auth的并不是token的
        if (AuthUtil.realAuthSplit(authorization)) {
            log.warn("This token is Auth {}", SecurityConstants.AUTH_SPLIT);
            return null;
        }


        String token = AuthUtil.getToken(authorization);
        if (StrUtil.isBlank(token)) {
            log.warn("resolveArgument error token is empty");
            return null;
        }

        User user = jwtTokenFactory.validateToken(token);
        if (user == null || user.getUserId() <= 0) {
            log.error("resolveArgument error token is not exist");
            throw new AuthException("validation failed");
        }

        return user;
    }

}
