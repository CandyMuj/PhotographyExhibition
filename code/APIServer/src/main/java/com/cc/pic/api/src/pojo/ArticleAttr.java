package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName MyTest
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
    private Integer orderIndex;
    private Integer itemType;
    private Long articleId;


    @Override
    protected Serializable pkVal() {
        return this.articleAttrId;
    }
}
