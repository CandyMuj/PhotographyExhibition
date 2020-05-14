package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
    import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ProjectName PhotographyExhibition
 * @FileName CategoryRelation
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRelation extends Model<CategoryRelation> {
    @TableId(type = IdType.INPUT)
    private Integer beanType;
    private Long beanId;
    private Long categoryId;


    @Override
    protected Serializable pkVal() {
        return this.beanType;
    }
}
