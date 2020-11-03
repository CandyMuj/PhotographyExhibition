package com.cc.pic.api.src.controller;

import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.LogType;
import com.cc.pic.api.src.enumc.SmsEnum;
import com.cc.pic.api.src.service.IThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @ProjectName LaiDianPay
 * @FileName SystemLogController
 * @Description 第三方服务的一些接口
 * @Author CandyMuj
 * @Date 2020/09/30 14:58
 * @Version 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/third")
@Api(tags = "第三方服务")
public class ThirdController extends BaseController {
    @Resource
    private IThirdService thirdService;


    @ApiOperation("短信-发送验证码")
    @PostMapping("/sms/send")
    public Result<String> sms(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(required = true, value = "手机号") @RequestParam @NotBlank(message = "手机号不可为空") String phone,
            @ApiParam(required = true, value = "验证码类型") @RequestParam Integer type
    ) {
        SmsEnum smsEnum = SmsEnum.val(type);
        if (smsEnum == null) {
            return Result.Error("不受支持的验证码类型");
        }

        return thirdService.sms(request, smsEnum, phone);
    }

    @ApiOperation("短信-校验验证码 此接口可能不会用到，验证写在业务接口中")
    @PostMapping("/sms/check")
    public Result<String> check(
            @ApiIgnore HttpServletRequest request,
            @ApiParam(required = true, value = "手机号") @RequestParam @NotBlank(message = "手机号不可为空") String phone,
            @ApiParam(required = true, value = "验证码") @RequestParam @NotBlank(message = "验证码不可为空") String smsCode,
            @ApiParam(required = true, value = "验证码类型") @RequestParam Integer type
    ) {
        SmsEnum smsEnum = SmsEnum.val(type);
        if (smsEnum == null) {
            return Result.Error("不受支持的验证码类型");
        }

        Result<String> result = thirdService.check(request, smsEnum, phone, smsCode);
        systemLogService.ins(LogType.IFACES, "短信-校验验证码", request, result);
        return result;
    }

    @ApiOperation("生成图形验证码")
    @GetMapping("/img/code")
    public void createImgCode(
            @ApiIgnore HttpServletResponse response,
            @ApiParam(required = true, value = "浏览器指纹") @RequestParam @NotBlank(message = "浏览器指纹不可为空") String fingerprint
    ) {
        thirdService.createImgCode(response, fingerprint);
    }

}
