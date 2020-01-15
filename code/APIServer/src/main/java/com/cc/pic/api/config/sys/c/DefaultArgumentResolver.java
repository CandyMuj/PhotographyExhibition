package com.cc.pic.api.config.sys.c;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProJectName APIServer
 * @FileName TokenArgumentResolver
 * @Description 入参默认值处理
 * @Author CandyMuj
 * @Date 2019/12/24 17:28
 * @Version 1.0
 */
@Slf4j
@Component
public class DefaultArgumentResolver implements HandlerMethodArgumentResolver {
    private static Map<Class, Object> defaultSettings = new HashMap<>();


    static {
        // 配置参数类型如果为空，那么设置对应的默认值
        defaultSettings.put(Integer.class, -1);
        defaultSettings.put(Double.class, -1);
        defaultSettings.put(Long.class, -1);
    }


    /**
     * 1. 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return defaultSettings.keySet().contains(methodParameter.getParameterType());
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
        String parameter = nativeWebRequest.getParameter(methodParameter.getParameterName());

        if (StrUtil.isBlank(parameter)) {
            PathVariable pathVariable = methodParameter.getParameterAnnotation(PathVariable.class);
            RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);
            ApiParam apiParam = methodParameter.getParameterAnnotation(ApiParam.class);

            if ((pathVariable != null && !pathVariable.required())
                    || (requestParam != null && !requestParam.required())
                    || apiParam != null && !apiParam.required()) {
                return defaultSettings.get(methodParameter.getParameterType());
            } else {
                return parameter;
            }
        }

        return parameter;
    }

}
