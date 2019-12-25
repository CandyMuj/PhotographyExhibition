package com.cc.pic.api.config.sys.c;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.utils.sys.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

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
        String token = nativeWebRequest.getHeader(REQ_HEADER);

        if (StrUtil.isBlank(token)) {
            log.warn("resolveArgument error token is empty");
            return null;
        }

        return JwtUtil.parse(token);
    }

}
