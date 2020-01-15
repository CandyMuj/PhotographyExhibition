package com.cc.pic.api.config.sys;

import com.cc.pic.api.config.sys.c.DefaultArgumentResolver;
import com.cc.pic.api.config.sys.c.TokenArgumentResolver;
import com.cc.pic.api.intercept.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProJectName APIServer
 * @FileName WebMvcConfig
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/25 11:05
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private TokenArgumentResolver tokenArgumentResolver;
    @Resource
    private DefaultArgumentResolver defaultArgumentResolver;
    @Resource
    private AuthInterceptor authInterceptor;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(tokenArgumentResolver);
        argumentResolvers.add(defaultArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // token校验放在最上面
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }

}
