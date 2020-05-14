package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
    import java.util.Date;

/**
 * @ProjectName PhotographyExhibition
 * @FileName Poster
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poster extends Model<Poster> {
    @TableId
    private Long posterId;
    private Long itemId;
    private Integer enabled;
    private String title;
    private String coverImg;
    private String itemUrl;
    private Integer orderIndex;
    private Date showTime;
    private Date addTime;
    private Integer itemType;
    private Integer postion;


    @Override
    protected Serializable pkVal() {
        return this.posterId;
    }
}
