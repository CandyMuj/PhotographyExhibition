package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Sysconfig
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:25
 * @Version 1.0
 */
public class SysConfig extends Model<SysConfig> {
    @TableId
    private Integer sysId;
    private String syskey;
    private String sysvalue;
    private String desc;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
