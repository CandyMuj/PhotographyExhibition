package com.cc.pic.api.annotations;

import com.cc.pic.api.enumc.ApiGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName api
 * @FileName ApiGroup
 * @Description
 * @Author CandyMuj
 * @Date 2020/7/17 17:01
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    // swagger文档分组注解
    ApiGroup[] apiGroup() default {};

}
