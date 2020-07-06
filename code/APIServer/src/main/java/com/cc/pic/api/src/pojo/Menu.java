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
 * @FileName Menu
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends Model<Menu> {
    @TableId
    @ApiModelProperty("菜单表")
    private Long menuId;
    private Long pid;
    @ApiModelProperty("菜单类型：管理端菜单，首页菜单...")
    private Integer menuType;
    private String menuUrl;
    private String menuName;
    @ApiModelProperty("是否可用")
    private Integer enabled;
    private Long orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }
}
