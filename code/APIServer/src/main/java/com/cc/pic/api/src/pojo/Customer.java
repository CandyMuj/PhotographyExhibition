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
 * @FileName Customer
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Model<Customer> {
    @TableId
    @ApiModelProperty("用户主表，登录信息")
    private Long customerId;
    @ApiModelProperty("冻结时间")
    private Date frozenTime;
    @ApiModelProperty("用户类型 1管理员 2普通用户 位运算（可登陆平台的权限，通过此字段定义枚举 安全可靠些）")
    private Integer userType;
    @ApiModelProperty("冻结原因")
    private String frozenReason;
    @ApiModelProperty("密码")
    private String passwd;
    @ApiModelProperty("是否冻结")
    private Integer frozen;
    @ApiModelProperty("用户账号")
    private String account;
    @ApiModelProperty("注册时间；添加时间")
    private Date addTime;
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;


    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }
}
