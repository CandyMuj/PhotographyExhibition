package com.cc.pic.api.src.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.LogType;
import com.cc.pic.api.src.mapper.SysConfigMapper;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.pojo.SysConfig;
import com.cc.pic.api.src.service.ISysConfigService;
import com.cc.pic.api.src.service.ISystemLogService;
import com.cc.pic.api.utils.sys.bean.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName PhotographyExhibition
 * @FileName SysConfigServiceImpl
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {
    @Resource
    private ISystemLogService systemLogService;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 新增或修改系统配置
     */
    @Override
    public Result<?> addOrUpd(HttpServletRequest request, Customer login, SysConfig sysConfig) {
        boolean upd = (sysConfig.getSysId() != null && sysConfig.getSysId() > 0);

        // 查询key是否重复
        Wrapper<SysConfig> wrapper = new EntityWrapper<SysConfig>().eq("sys_key", sysConfig.getSysKey());
        if (upd) {
            wrapper.ne("sys_id", sysConfig.getSysId());
        }
        SysConfig check = super.selectOne(wrapper);
        if (check != null) {
            return Result.Error("key 已存在！");
        }

        // 更改前的数据
        SysConfig oldData = super.selectById(sysConfig.getSysId());


        if (!sysConfig.insertOrUpdate()) {
            return Result.Error();
        }


        // 新增日志
        systemLogService.add(LogType.ADMIN, login.getCustomerId(),
                (upd ? "修改系统配置" : "新增系统配置"),
                request,
                (upd ? JSONObject.toJSONString(oldData) : null)
        );

        return Result.OK();
    }

}
