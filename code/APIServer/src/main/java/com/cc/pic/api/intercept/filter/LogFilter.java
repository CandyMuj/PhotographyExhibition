package com.cc.pic.api.intercept.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProJectName APIServer
 * @FileName LogFilter
 * @Description 日志过滤器
 * @Author CandyMuj
 * @Date 2019/12/26 14:21
 * @Version 1.0
 * <p>
 * <p>
 * 三大器的执行顺序，经过测试得到的结论
 * 过滤器（filter 全局异常不可捕获）>拦截器（interceptor 全局异常可捕获）>ArgumentResolvers（参数转换)>AOP（全局异常不可捕获）
 *
 * <p>
 * 关于aop执行说明
 * 1、aop粒度到方法类返回值等，在方法上做切面做拦截的一种技术
 * 2、如果你的切点定义为某一个包下的所有类中的所有方法
 * 3、如果访问此包下摸一个方法，而此方法又调用了其他的bean，且此bean也在切点内（除了静态方法 因为aop拦截的就是方法层面的，那肯定只能拦截通过ioc注入通过spring管理的才行，静态方法它控制不了监控不了）
 * 4、由于spring都是以ioc的方式管理对象，所以如果访问service这些会再次进入aop，因为这个service也在切点，那么会再次执行这个aop
 * 5、此时的执行流程就是：即将执行切点下的controller->进入aop 并放行->执行controller->执行切点下的service bean->进入aop 并放行->执行service
 * 6、如果还会执行其他的bean，只要是bean都是被spring的ioc所控制，自然都能拦截，那么以此类推每多执行一个bean中的方法，aop就会被执行一次，也就是说aop在某些切点的配置下会被多次执行
 * 7、切点下如果包含，哪怕是拦截器或者过滤器的定义，只要是使用ioc注入的方式加入到spring中那么每次执行一个拦截器或过滤器也都会进入一次aop，除非你是直接new的
 * 8、过滤器和拦截器的
 */
@Slf4j
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            log.info("URL : " + request.getRequestURL().toString());
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
