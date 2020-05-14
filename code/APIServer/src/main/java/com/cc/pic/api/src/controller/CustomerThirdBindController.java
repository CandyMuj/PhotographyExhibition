package com.cc.pic.api.src.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName MyTest
 * @FileName CustomerThirdBindController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/customerThirdBind")
@Api(tags = "第三方账号关联绑定，主要用于登录，所以这里使用cusid")
public class CustomerThirdBindController {

}
