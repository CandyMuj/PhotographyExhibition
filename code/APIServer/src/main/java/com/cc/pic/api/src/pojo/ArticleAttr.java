package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProjectName PhotographyExhibition
 * @FileName ArticleAttr
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAttr extends Model<ArticleAttr> {
    @TableId
    private Long articleAttrId;
    private Long itemId;
    private String location;
    private Long orderIndex;
    private Integer itemType;
    private Long articleId;


    @Override
    protected Serializable pkVal() {
        return this.articleAttrId;
    }
}
