package com.cc.pic.api.src.service;

import com.baomidou.mybatisplus.service.IService;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.pojo.SysConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName PhotographyExhibition
 * @FileName ISysConfigService
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
public interface ISysConfigService extends IService<SysConfig> {

    Result<?> addOrUpd(HttpServletRequest request, Customer customer, SysConfig sysConfig);

    Result<List<SysConfig>> list();
}
