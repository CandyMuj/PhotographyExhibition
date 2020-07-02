package com.cc.pic.api.src.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.pojo.Customer;

/**
 * @ProjectName PhotographyExhibition
 * @FileName ICustomerService
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
public interface ICustomerService extends IService<Customer> {

    Result<JSONObject> adminLogin(String account, String password);
}
