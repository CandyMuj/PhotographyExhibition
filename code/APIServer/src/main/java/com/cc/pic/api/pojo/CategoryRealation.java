package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName CategoryRealation
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:01
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRealation extends Model<CategoryRealation> {
    private Integer beanType;
    private Integer beanId;
    private Integer categoryId;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
