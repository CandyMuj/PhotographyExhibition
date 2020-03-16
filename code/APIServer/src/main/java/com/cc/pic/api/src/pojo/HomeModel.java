package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName HomeModel
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:43
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeModel extends Model<HomeModel> {
    @TableId
    private Integer homeModelId;
    private String title;
    private String desc;
    private String btnConfig;
    private String pic;
    private Integer orderIndex;
    private Integer modelType;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
