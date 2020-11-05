package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName LaiDianPay
 * @FileName CustomerRole
 * @Description
 * @Author CandyMuj
 * @Date 2020/11/05 11:52
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRole extends Model<CustomerRole> {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty("用户与菜单id关联表（权限关联表）")
    private Long customerId;
    @ApiModelProperty("用户类型 1管理员 2普通用户 位运算（可登陆平台的权限，通过此字段定义枚举 安全可靠些）")
    private Integer userType;
    @ApiModelProperty("菜单id")
    private String menuId;


    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }
}
