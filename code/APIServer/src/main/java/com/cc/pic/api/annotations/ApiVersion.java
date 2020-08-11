package com.cc.pic.api.annotations;

import com.cc.pic.api.enumc.ApiGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName api
 * @FileName ApiGroup
 * @Description 用作swagger分组，可作用于controller类和controller方法中，可同时使用，方法上的注解优先级最大
 * 本来在考虑是否将此注解和Ann注解进行合并，但是思考了一下不做合并，因为Ann主要用作鉴权，整个类都需要鉴权的情况并不多见，因为一个类肯定是个别需要鉴权，个别不需要，所有还是设置为只作用于方法即可
 * 如果后续有类似作用于方法更频繁的场景就可以考虑和ann合并
 * 而分组根据整个类来注解的情况相对而言更多一点，针对单独的方法也存在，所以二者并存，方法的优先级更高即可，因此针对类的注解的场景更多的情况下可以考虑使用此注解
 * <p>
 * 这样做可以解耦，因为文档和ann中的注解内容并不一样，ann中的内容更多的是涉及到框架和业务相关的，而这个就只是文档没有什么实际的意义，简而言之可有可无，但是ann中的是必须要的
 * @Author CandyMuj
 * @Date 2020/7/17 17:01
 * @Version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    // 必填值：swagger文档分组注解
    ApiGroup[] value();

}
