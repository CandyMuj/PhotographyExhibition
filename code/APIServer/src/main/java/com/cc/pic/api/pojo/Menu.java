package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Menu
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends Model<Menu> {
    @TableId
    private Integer menuId;
    private String menuName;
    private String url;
    private Integer pid;
    private Integer type;
    private Integer orderindex;
    private Integer enable;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
