package com.cc.pic.api.src.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.UserTypeEnum;
import com.cc.pic.api.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CustomerController
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/customer")
@Api(tags = "用户登录、注册")
public class CustomerController extends BaseController {

    @ApiOperation(UserTypeEnum.ADMIN_CLIENT_STR + "登录")
    @PostMapping("/admin/login")
    public Result<JSONObject> adminLogin(
            @ApiParam(required = true, value = "账号") @RequestParam @NotBlank(message = "账号不可为空") String account,
            @ApiParam(required = true, value = "密码") @RequestParam @NotBlank(message = "密码不可为空") String password
    ) {
        return super.customerService.adminLogin(account, MD5.MD5Encode(password));
    }

}
