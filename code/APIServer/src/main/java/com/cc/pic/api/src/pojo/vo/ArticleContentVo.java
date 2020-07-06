package com.cc.pic.api.src.pojo.vo;

import com.cc.pic.api.src.pojo.ArticleContent;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName MyTest
 * @FileName ArticleContent
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@ApiModel("文章内容表 图文，对应文章id，一个文章只有一个内容")
public class ArticleContentVo extends ArticleContent {

}