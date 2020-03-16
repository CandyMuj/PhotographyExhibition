package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProJectName APIServer
 * @FileName Article
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/3 16:50
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Model<Article> {
    @TableId
    private Integer articleId;
    private String title;
    private String face;
    private Integer articleType;
    private Integer articleType2;
    private Integer enable;
    private Integer customerBusType;
    private Integer customerId;
    private Integer viewCount;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
