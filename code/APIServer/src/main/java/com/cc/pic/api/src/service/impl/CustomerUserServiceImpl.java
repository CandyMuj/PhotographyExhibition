package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.src.mapper.CustomerUserMapper;
import com.cc.pic.api.src.pojo.CustomerUser;
import com.cc.pic.api.src.service.ICustomerUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CustomerUserServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Service
public class CustomerUserServiceImpl extends ServiceImpl<CustomerUserMapper, CustomerUser> implements ICustomerUserService {

}
