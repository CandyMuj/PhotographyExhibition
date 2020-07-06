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
 * @FileName CustomerUser
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUser extends Model<CustomerUser> {
    @TableId
    @ApiModelProperty("用户表-普通用户")
    private Long customerUserId;
    @ApiModelProperty("冻结时间")
    private Date frozenTime;
    @ApiModelProperty("冻结原因")
    private String frozenReason;
    @ApiModelProperty("用户真实姓名 用于图片署名作者")
    private String userRealName;
    @ApiModelProperty("是否冻结")
    private Integer frozen;
    @ApiModelProperty("用户昵称 用户平台展示昵称")
    private String userNickName;
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;
    @ApiModelProperty("新增时间")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.customerUserId;
    }
}
