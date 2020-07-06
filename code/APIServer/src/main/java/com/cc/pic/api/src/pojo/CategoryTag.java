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
 * @FileName CategoryTag
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTag extends Model<CategoryTag> {
    @TableId
    @ApiModelProperty("分类和标签")
    private Long categoryTagId;
    @ApiModelProperty("名称")
    private String categoryName;
    @ApiModelProperty("分类类型/位置")
    private Integer moduleType;
    @ApiModelProperty("上级分类")
    private Long pid;
    @ApiModelProperty("排序 - 越大排序越前")
    private Long orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.categoryTagId;
    }
}
