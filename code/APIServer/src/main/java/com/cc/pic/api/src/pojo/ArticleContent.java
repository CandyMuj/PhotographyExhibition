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
 * @FileName ArticleContent
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent extends Model<ArticleContent> {
    @TableId(type = IdType.INPUT)
    @ApiModelProperty("文章内容表 图文，对应文章id，一个文章只有一个内容")
    private Long articleId;
    @ApiModelProperty("文章内容")
    private String content;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }
}
