package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Category
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 14:58
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Model<Category> {
    @TableId
    private Integer categoryId;
    private Integer pid;
    private Integer moduleType;
    private String cname;
    private Integer orderIndex;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
