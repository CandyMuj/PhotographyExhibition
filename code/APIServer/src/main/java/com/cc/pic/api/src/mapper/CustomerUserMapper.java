package com.cc.pic.api.src.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cc.pic.api.src.pojo.CustomerUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CustomerUserMapper
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Mapper
@Component
public interface CustomerUserMapper extends BaseMapper<CustomerUser> {

}
