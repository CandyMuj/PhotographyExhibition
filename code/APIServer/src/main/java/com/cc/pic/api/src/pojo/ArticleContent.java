package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
    import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ProjectName MyTest
 * @FileName ArticleContent
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent extends Model<ArticleContent> {
    @TableId(type = IdType.INPUT)
    private Long articleId;
    private String content;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }
}
