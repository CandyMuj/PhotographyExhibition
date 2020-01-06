package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Role
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:24
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Model<Role> {
    @TableId
    private Integer roleId;
    private String name;
    private Integer status;
    private String menuId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
