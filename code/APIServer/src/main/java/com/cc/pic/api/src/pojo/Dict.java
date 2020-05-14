package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName MyTest
 * @FileName Dict
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict extends Model<Dict> {
    @TableId
    private Long dictId;
    private String dictType;
    private Integer selfLimit;
    private String dictName;
    private String dictCode;
    private String remark;
    private String extData;
    private Long dictPid;
    private Integer childrenLimit;
    private Integer orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }
}
