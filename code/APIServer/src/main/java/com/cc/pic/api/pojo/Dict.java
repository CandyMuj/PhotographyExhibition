package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Dict
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict extends Model<Dict> {
    @TableId
    private Integer dictId;
    private Integer dictpid;
    private String dictname;
    private String dictcode;
    private String dicttype;
    private Integer orderindex;
    private String remark;
    private Integer selfLimit;
    private Integer childrenLimit;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
