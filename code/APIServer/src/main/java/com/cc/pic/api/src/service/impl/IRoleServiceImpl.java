package com.cc.pic.api.src.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.src.pojo.Role;
import com.cc.pic.api.src.mapper.RoleMapper;
import com.cc.pic.api.src.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProJectName APIServer
 * @FileName IRoleServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/7 15:48
 * @Version 1.0
 */
@Slf4j
@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
