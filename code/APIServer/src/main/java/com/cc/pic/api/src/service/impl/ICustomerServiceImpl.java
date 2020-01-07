package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.Customer;
import com.cc.pic.api.src.mapper.CustomerMapper;
import com.cc.pic.api.src.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProJectName APIServer
 * @FileName ICustomerServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/7 15:48
 * @Version 1.0
 */
@Slf4j
@Service
public class ICustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
