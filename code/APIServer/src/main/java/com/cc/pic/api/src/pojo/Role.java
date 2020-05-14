package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName MyTest
 * @FileName Role
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Model<Role> {
    @TableId
    private Long roleId;
    private String btnId;
    private Integer enabled;
    private Integer roleType;
    private String roleName;
    private String menuId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }
}
