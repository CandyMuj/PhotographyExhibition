package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName MyTest
 * @FileName CategoryRelation
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRelation extends Model<CategoryRelation> {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty("关联实体类型 使用枚举")
    private Integer beanType;
    @ApiModelProperty("分类标签与实体的关联表")
    private Long categoryId;
    @ApiModelProperty("关联实体id")
    private Long beanId;


    @Override
    protected Serializable pkVal() {
        return this.beanType;
    }
}
