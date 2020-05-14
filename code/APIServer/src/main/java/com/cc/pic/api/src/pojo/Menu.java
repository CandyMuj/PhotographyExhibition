package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName PhotographyExhibition
 * @FileName Menu
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends Model<Menu> {
    @TableId
    private Long menuId;
    private Long pid;
    private Integer menuType;
    private Integer enabled;
    private String menuUrl;
    private String menuName;
    private Integer orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }
}
