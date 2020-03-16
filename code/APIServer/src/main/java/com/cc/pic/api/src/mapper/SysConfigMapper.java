package com.cc.pic.api.src.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cc.pic.api.src.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @ProJectName APIServer
 * @FileName SysConfigMapper
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/7 9:48
 * @Version 1.0
 */
@Mapper
@Component
public interface SysConfigMapper extends BaseMapper<SysConfig> {
}
