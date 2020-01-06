package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName ArticleContent
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 14:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent extends Model<ArticleContent> {
    @TableId(type = IdType.INPUT)
    private Integer articleId;
    private String content;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
