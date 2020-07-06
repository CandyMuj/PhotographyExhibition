package com.cc.pic.api.src.pojo.vo;

import com.cc.pic.api.src.pojo.ArticleAttr;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName MyTest
 * @FileName ArticleAttr
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@ApiModel("文章属性关联表，如果有关联或属性此表才有对应的数据")
public class ArticleAttrVo extends ArticleAttr {

}