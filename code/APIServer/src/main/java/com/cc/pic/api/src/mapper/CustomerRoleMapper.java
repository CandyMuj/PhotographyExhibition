package com.cc.pic.api.src.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cc.pic.api.src.pojo.CustomerRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ProjectName MyTest
 * @FileName CustomerRoleMapper
 * @Description
 * @Author CandyMuj
 * @Date 2020/11/05 11:52
 * @Version 1.0
 */
@Mapper
@Component
public interface CustomerRoleMapper extends BaseMapper<CustomerRole> {

}
