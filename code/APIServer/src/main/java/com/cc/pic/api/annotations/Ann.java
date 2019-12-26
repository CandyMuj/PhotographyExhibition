package com.cc.pic.api.annotations;

import java.lang.annotation.*;

/**
 * @ProJectName APIServer
 * @FileName Ann
 * @Description 作用于接口上的注解，一些自定义的配置 如此接口是否需要鉴权;其实接口鉴权还可以如之前的项目做一个鉴权库，但是至今用注解效率显然更高这是更优的选择
 * @Author CandyMuj
 * @Date 2019/12/25 16:54
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Ann {

    // 此接口是否需要登录鉴权
    boolean au() default true;

}
