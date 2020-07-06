package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName MyTest
 * @FileName Role
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Model<Role> {
    @TableId
    @ApiModelProperty("角色权限库")
    private Long roleId;
    @ApiModelProperty("按钮id")
    private String btnId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("0禁用 1启用")
    private Integer enabled;
    @ApiModelProperty("角色作用的位置，如：管理端还是用户端")
    private Integer roleType;
    @ApiModelProperty("菜单id 二选一即可，暂时只做到菜单级别的权限")
    private String menuId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}
