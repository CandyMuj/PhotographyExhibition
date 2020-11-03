package com.cc.pic.api.src.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cc.pic.api.pojo.sys.Result;
import com.cc.pic.api.src.config.CacheKey;
import com.cc.pic.api.src.enumc.LogType;
import com.cc.pic.api.src.enumc.SmsEnum;
import com.cc.pic.api.src.pojo.restpo.OperatorInfo;
import com.cc.pic.api.src.service.ISystemLogService;
import com.cc.pic.api.src.service.IThirdService;
import com.cc.pic.api.utils.VerifyCodeUtils;
import com.cc.pic.api.utils.sys.YmlConfig;
import com.cc.pic.api.utils.sys.bean.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * @ProjectName api
 * @FileName ThirdServiceImpl
 * @Description 第三方服务接口封装类
 * @Author CandyMuj
 * @Date 2020/10/9 9:31
 * @Version 1.0
 */
@Slf4j
@Service
public class ThirdServiceImpl implements IThirdService {
    @Resource
    private ISystemLogService systemLogService;
    @Resource
    private RedisUtil redisUtil;


    /**
     * 根据手机号获取运营商信息
     *
     * @param phone 手机号
     */
    @Override
    public OperatorInfo ispInfo(String phone) {
        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=%s";

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet(String.format(url, phone));
            String res = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), Charset.defaultCharset()).replace("__GetZoneResult_ = ", "");
            JSONObject jobj = JSON.parseObject(res);

            OperatorInfo operatorInfo = new OperatorInfo();
            operatorInfo.setPhone(jobj.getString("telString"));
            operatorInfo.setProvince(jobj.getString("province"));
            operatorInfo.setIspName(jobj.getString("catName"));
            operatorInfo.setAreaId(jobj.getString("areaVid"));
            operatorInfo.setIspId(jobj.getString("ispVid"));
            operatorInfo.setCarrier(jobj.getString("carrier"));
            operatorInfo.setMts(jobj.getString("mts"));

            if (!phone.equals(operatorInfo.getPhone())) {
                throw new RuntimeException("返回结果与预期不一致");
            }

            // 添加系统日志
            systemLogService.add(LogType.IFACEO, "[成功]获取运营商信息", url, phone);
            return operatorInfo;
        } catch (Exception e) {
            log.error("获取运营商信息异常", e);
            // 添加系统日志
            systemLogService.add(LogType.IFACEO, "[异常]获取运营商信息".concat(e.getClass().getName()).concat(e.getMessage()), url, phone);
            return null;
        }
    }

    /**
     * 发送短信验证码
     */
    @Override
    public Result<String> sms(HttpServletRequest request, SmsEnum smsEnum, String phone) {
        try {
            String code = RandomUtil.randomNumbers(4);
            if (YmlConfig.getBoolean("src.third.sms.enable")) {
                String smsres = null;   //todo TencentSmsUtil.sendSms(phone, SrcConfig.TENCENT_SMS_APPID, SrcConfig.TENCENT_SMS_SIGN, SmsTemplateCode.SMS_CODE, new String[]{code});
                if (smsres != null) {
                    return Result.Error(smsres);
                }
            }

            // 存入redis
            redisUtil.set(CacheKey.smsCode(phone, smsEnum), code, YmlConfig.getIntValue("src.third.sms.timeout") * 60);

            // 新增日志
            if (request != null) {
                systemLogService.ins(LogType.IFACES, "发送短信验证码",
                        request,
                        Result.OK("验证码为:".concat(code))
                );
            }
            return Result.OK();
        } catch (Exception e) {
            log.error("发送短信失败异常", e);
            return Result.Error();
        }
    }

    /**
     * 验证码校验
     */
    @Override
    public Result<String> check(HttpServletRequest request, SmsEnum smsEnum, String phone, String smsCode) {
        String defaultCode = YmlConfig.getString("src.third.sms.default");
        if (StrUtil.isNotBlank(defaultCode) && defaultCode.equals(smsCode)) {
            return Result.OK();
        } else {
            // 验证验证码
            Object code = redisUtil.get(CacheKey.smsCode(phone, smsEnum));
            if (code == null || !code.equals(smsCode)) {
                return Result.Error("验证码错误");
            }
        }
        // 如果使用默认验证码这里也要删除一下，防止旧数据出现漏洞
        redisUtil.del(CacheKey.smsCode(phone, smsEnum));
        return Result.OK();
    }

    /**
     * 生成图形验证码
     *
     * @param fingerprint 浏览器指纹，用来确保验证的时候是同一个浏览器发起的，不然a用b的验证码也可能验证成功
     */
    @Override
    public void createImgCode(HttpServletResponse response, String fingerprint) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入缓存 不区分大小写
        redisUtil.set(CacheKey.imgCode(fingerprint), verifyCode.toLowerCase(), 60 * 60);

        //生成图片
        int width = 100;//宽
        int height = 40;//高
        try (ServletOutputStream out = response.getOutputStream()) {
            VerifyCodeUtils.outputImage(width, height, out, verifyCode);
        } catch (Exception e) {
            log.error("输出图形验证码异常", e);
        }
    }

    /**
     * 图形验证码校验
     */
    @Override
    public boolean checkImgCode(String fingerprint, String imgCode) {
        // 验证验证码
        Object code = redisUtil.get(CacheKey.imgCode(fingerprint));
        if (StrUtil.isBlank(fingerprint) || code == null || !code.equals(imgCode.toLowerCase())) {
            return false;
        }

        redisUtil.del(CacheKey.imgCode(fingerprint));
        return true;
    }

}
