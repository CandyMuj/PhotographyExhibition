package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * @ProjectName MyTest
 * @FileName CustomerThirdBind
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerThirdBind extends Model<CustomerThirdBind> {
    @TableId
    @ApiModelProperty("第三方账号关联绑定，主要用于登录，所以这里使用cusid")
    private Long customerThirdBindId;
    @ApiModelProperty("用户id")
    private Long customerId;
    @ApiModelProperty("密钥类型 手机号，微信，qq")
    private Integer secretType;
    @ApiModelProperty("关联时间")
    private Date addTime;
    @ApiModelProperty("对应的值")
    private String secretKey;


    @Override
    protected Serializable pkVal() {
        return this.customerThirdBindId;
    }
}
