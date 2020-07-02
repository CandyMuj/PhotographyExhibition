package com.cc.pic.api.src.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.enumc.Enable;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.src.enumc.UserTypeEnum;
import com.cc.pic.api.src.mapper.CustomerMapper;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.service.ICustomerService;
import com.cc.pic.api.utils.sys.bean.JwtTokenFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CustomerServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Resource
    private JwtTokenFactory jwtTokenFactory;


    @Override
    @Transactional
    public Result<JSONObject> adminLogin(String account, String password) {
        Customer customer = super.selectOne(new EntityWrapper<Customer>().eq("account", account));
        if (customer == null || (customer.getUserType() & UserTypeEnum.ADMIN.getType()) != UserTypeEnum.ADMIN.getType()) {
            return Result.Error("账号不存在");
        }
        if (!password.equals(customer.getPasswd())) {
            return Result.Error("用户名或或密码错误");
        }
        if (!Enable.DISENABLE.getCode().equals(customer.getFrozen())) {
            return Result.Error("您的账号已被冻结");
        }

        String jwttoken = getToken(customer.getCustomerId());
        if (StrUtil.isBlank(jwttoken)) {
            return Result.Error("登录失败");
        }

        customer.setLastLoginTime(new Date());
        if (customer.updateById()) {
            customer.setPasswd(null);

            JSONObject jobj = new JSONObject();
            jobj.put("customer", customer);
            jobj.put("token", jwttoken);

            return Result.OK(jobj);
        } else {
            return Result.Error("登录失败");
        }
    }


    private String getToken(Long accountId) {
        // 生成token
        User user = new User();
        user.setUserId(accountId);
        return jwtTokenFactory.generateToken(user);
    }


}
