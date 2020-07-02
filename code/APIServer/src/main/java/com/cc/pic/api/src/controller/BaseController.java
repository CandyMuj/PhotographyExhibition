package com.cc.pic.api.src.controller;

import com.cc.pic.api.src.service.ICustomerService;

import javax.annotation.Resource;

/**
 * @ProjectName api
 * @FileName BaseController
 * @Description
 * @Author CandyMuj
 * @Date 2020/6/9 14:30
 * @Version 1.0
 */
public class BaseController {
    @Resource
    protected ICustomerService customerService;



}
