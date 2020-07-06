package com.cc.pic.api.src.pojo.vo;

import com.cc.pic.api.src.pojo.CustomerThirdBind;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName MyTest
 * @FileName CustomerThirdBind
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@ApiModel("第三方账号关联绑定，主要用于登录，所以这里使用cusid")
public class CustomerThirdBindVo extends CustomerThirdBind {

}