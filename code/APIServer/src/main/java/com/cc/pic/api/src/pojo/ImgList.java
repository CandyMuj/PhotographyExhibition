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
 * @FileName Imglist
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:38
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgList extends Model<ImgList> {
    @TableId
    private Integer imgListId;
    private Integer itemType;
    private Integer itemId;
    private String url;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
