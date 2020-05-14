package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.src.mapper.CustomerMapper;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
