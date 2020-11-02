package com.cc.pic.api.src.controller;

import com.cc.pic.api.enumc.Enable;
import com.cc.pic.api.src.enumc.UserTypeEnum;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.service.ICustomerService;
import com.cc.pic.api.src.service.ISystemLogService;

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
    @Resource
    protected ISystemLogService systemLogService;


    /**
     * 验证当前账号是否具有管理员操作权限
     */
    protected String validateAdmin(Customer customer) {
        if (customer == null) {
            return "账号不存在";
        }
        if (!this.isAdmin(customer)) {
            return "无权操作";
        }
        if (!Enable.DISENABLE.getCode().equals(customer.getFrozen())) {
            return "此账号被冻结";
        }

        return null;
    }


    private boolean isAdmin(Customer customer) {
        return customer != null && UserTypeEnum.ADMIN.getType().equals(customer.getUserType());
    }

}
