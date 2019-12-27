package com.cc.pic.api.intercept.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @ProJectName APIServer
 * @FileName ControllerAop
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/25 17:14
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {

    /**
     * 定义一个切点
     * 拦截所有请求；用于作日志打印
     */
    @Pointcut("within(com.cc.pic.api..*.*Controller)")
    // @Pointcut("execution(public * com.cc.pic.api..*.*(..))")
    public void cutOfAll() {
    }

    /**
     * 定义一个切点
     * 拦截返回值为Result的请求；用于返回值的二次处理
     */
//    @Pointcut("execution(public com.cc.pic.api.pojo.sys.Result *(..))")
//    public void cutOfResult() {
//    }
//
//
//    @AfterReturning(returning = "result", pointcut = "cutOfResult()")
//    public void cutOfResult(Object result) {
//
//        System.out.println(result);
//    }

    /**
     * 拦截器具体实现
     * <p>
     * 拦截所有请求，打印请求日志
     */
    @Around("cutOfAll()")
    public Object cutOfAll(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        if (attributes != null && signature != null) {
            log.info("CLASS_METHOD : " + signature.getDeclaringTypeName() + "." + signature.getName());
            log.info("ARGS : " + Arrays.toString(pjp.getArgs()));
        }

        // 绝对不能因为上方判断为空就返回 Result.Error 因为这个切点是拦截的所有请求，并不是所有的都返回的是Result 有可能是其他对象，甚至是流;那样就篡改了真实的返回数据了
        Object result = pjp.proceed();
        log.info(pjp.getSignature() + " use time: " + (System.currentTimeMillis() - startTime));
        return result;
    }

}
