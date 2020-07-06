package com.cc.pic.api.src.pojo.vo;

import com.cc.pic.api.src.pojo.CategoryRelation;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName MyTest
 * @FileName CategoryRelation
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@ApiModel("分类标签与实体的关联表，想了许久是直接在业务bean中加一个标签字段，还是使用专门的关系表来进行管理，最后决定使用关系表，就是分表吧")
public class CategoryRelationVo extends CategoryRelation {

}