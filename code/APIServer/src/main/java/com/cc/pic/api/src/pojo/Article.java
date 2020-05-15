package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName PhotographyExhibition
 * @FileName Article
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Model<Article> {
    @TableId
    private Long articleId;
    private Integer enabled;
    private String title;
    private Integer readNum;
    private Integer articleType2;
    private String coverImg;
    private Integer articleType;
    private Date addTime;
    private Long customerId;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }
}
