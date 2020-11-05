package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName LaiDianPay
 * @FileName Menu
 * @Description
 * @Author CandyMuj
 * @Date 2020/11/05 11:31
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
    @ApiModelProperty("菜单类型：目录、菜单、按钮（必须区分，目录和按钮都没有url，所以不能仅通过url是否为空来判断）")
    private Integer menuType;
    private String menuUrl;
    private String menuName;
    @ApiModelProperty("是否可用")
    private Integer enabled;
    @ApiModelProperty("客户端类型：管理端、安卓端、pc端、用户端、商家端...")
    private Integer clientType;
    private Long orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }
}
