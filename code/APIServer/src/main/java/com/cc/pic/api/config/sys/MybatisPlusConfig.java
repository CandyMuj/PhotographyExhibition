package com.cc.pic.api.config.sys;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProJectName api
 * @FileName MybatisPlusConfig
 * @Description
 * @Author CandyMuj
 * @Date 2020/3/26 16:14
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis-plus 的分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
