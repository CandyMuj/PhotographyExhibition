package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CategoryTag
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTag extends Model<CategoryTag> {
    @TableId
    private Long categoryTagId;
    private Long pid;
    private Integer moduleType;
    private Long orderIndex;
    private String categoryName;


    @Override
    protected Serializable pkVal() {
        return this.categoryTagId;
    }
}
