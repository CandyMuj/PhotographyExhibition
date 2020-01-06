package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProJectName APIServer
 * @FileName Poster
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:06
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poster extends Model<Poster> {
    @TableId
    private Integer posterId;
    private Integer postion;
    private String title;
    private String face;
    private Integer itemType;
    private Integer itemId;
    private String url;
    private Integer enable;
    private Integer orderIndex;
    private Date showTime;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
