package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName MyTest
 * @FileName SysConfig
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig extends Model<SysConfig> {
    @TableId
    private Long sysId;
    private String sysDesc;
    private String sysValue;
    private String sysKey;


    @Override
    protected Serializable pkVal() {
        return this.sysId;
    }
}
