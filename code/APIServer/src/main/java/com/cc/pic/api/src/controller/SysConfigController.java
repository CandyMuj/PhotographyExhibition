package com.cc.pic.api.src.controller;

import com.cc.pic.api.annotations.Ann;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.pojo.sys.User;
import com.cc.pic.api.src.pojo.Customer;
import com.cc.pic.api.src.pojo.SysConfig;
import com.cc.pic.api.src.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @ProjectName PhotographyExhibition
 * @FileName SysConfigController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/system/config")
@Api(tags = "系统配置")
public class SysConfigController extends BaseController {
    @Resource
    private ISysConfigService sysConfigService;


    @Ann
    @ApiOperation("管理端-新增/修改系统配置")
    @PostMapping("/addOrUpd")
    public Result<?> addOrUpd(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(required = false, value = "配置ID 不为空则修改") @RequestParam(required = false) Long sysId,
            @ApiParam(required = true, value = "配置键名") @RequestParam @NotBlank(message = "键名不可为空") String sysKey,
            @ApiParam(required = true, value = "配置值") @RequestParam @NotBlank(message = "值不可为空") String sysValue,
            @ApiParam(required = true, value = "配置描述") @RequestParam @NotBlank(message = "配置描述") String sysDesc,
            @ApiIgnore User user
    ) {
        Customer customer = customerService.selectById(user.getUserId());
        String msg = super.validateAdmin(customer);
        if (msg != null) {
            return Result.Error(msg);
        }


        SysConfig sysConfig = new SysConfig();
        sysConfig.setSysId(sysId);
        sysConfig.setSysKey(sysKey);
        sysConfig.setSysValue(sysValue);
        sysConfig.setSysDesc(sysDesc);

        return sysConfigService.addOrUpd(request, customer, sysConfig);
    }

}
