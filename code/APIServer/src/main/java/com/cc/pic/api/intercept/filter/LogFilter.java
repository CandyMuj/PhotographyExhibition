package com.cc.pic.api.intercept.filter;

import com.cc.pic.api.utils.IpUtil;
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
 * 拦截器>执行Controller定义（非方法体，就是如果controller有注解如非空校验reqparam或notblank就会执行，他会先执行方法的定义加载到内存）>AOP>aop放行后才会执行controller中具体的方法体，里面的代码逻辑
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
 * 8、如果过滤器的定义也在切点下且过滤器是通过ioc注入，按理说是先执行过滤器，确实是，但是这个时候由于也在切点下，就会认为是在执行切点下的dofilter（包括其他方法）方法，也会被aop拦截，所以就会先执行aop了
 * 9、过滤器和拦截器的定义注入方式一般都有 new的方式和直接注入的方式，ioc有个最大的特点，注入的对象只有一个相当于此对象只被new了一次，如果是哟共new的方式注入，那么就是一个全新的对象，哪怕执行注入操作的那个对象是ioc注入的，但是注入的是一个new的，那么被注入的就是new的和外层无关，他就是一个全新的对象手动new的和spring脱离关系脱离控制，所以切面无法拦截无任何关系，就只是单纯的注册了一个对象进去而已
 * * Filter：
 * *     1、（ioc）加@Component注解，springboot会自动扫描并注入（需要@ComponentScan支持）
 * *     2、（new）通过配置类中使用FilterRegistrationBean进行注册，直接new一个进行注册
 * *     3、（ioc）通过配置类中使用FilterRegistrationBean进行注册，使用ioc注入后再将注入的对象拿去注册（被注入对象需使用注解注册@Component）
 * *     4、（ioc）使用@WebFilter + @ServletComponentScan进行注入，与法一类似，使用注解自动扫描，但是既然基础的注解能实现注入（法一），那么没必要使用过多的注解或不常用的注解。不建议使用
 * * Interceptor：
 * *     1、（new）通过配置类WebMvcConfigurer，实现方法addInterceptors,直接new一个进行注册
 * *     2、（ioc）通过配置类WebMvcConfigurer，实现方法addInterceptors,使用ioc注入后再将注入的对象拿去注册（被注入对象需使用注解注册@Component）
 * *
 * 10、既然是使用的spring，那么还是建议使用ioc，尽量减少new的操作
 */
@Slf4j
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            log.info("IP : {}-{}", request.getRemoteAddr(), IpUtil.getRealIP(request));
            log.info("URL : {}", request.getRequestURL().toString());
            log.info("HTTP_METHOD : {}", request.getMethod());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
