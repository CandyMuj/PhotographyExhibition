package com.cc.pic.api.src.service;

import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.enumc.SmsEnum;
import com.cc.pic.api.src.pojo.restpo.OperatorInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName api
 * @FileName IThirdService
 * @Description
 * @Author CandyMuj
 * @Date 2020/10/9 9:31
 * @Version 1.0
 */
public interface IThirdService {

    /**
     * 根据手机号获取运营商信息
     *
     * @param phone 手机号
     */
    OperatorInfo ispInfo(String phone);

    Result<String> sms(HttpServletRequest request, SmsEnum smsEnum, String phone);

    Result<String> check(HttpServletRequest request, SmsEnum smsEnum, String phone, String smsCode);

    void createImgCode(HttpServletResponse response, String fingerprint);

    boolean checkImgCode(String fingerprint, String imgCode);
}
