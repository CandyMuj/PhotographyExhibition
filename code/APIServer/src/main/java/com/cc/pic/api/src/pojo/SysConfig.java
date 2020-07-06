package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName MyTest
 * @FileName SysConfig
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig extends Model<SysConfig> {
    @TableId
    @ApiModelProperty("系统配置")
    private Long sysId;
    private String sysDesc;
    private String sysKey;
    @ApiModelProperty("推荐使用json格式数据")
    private String sysValue;


    @Override
    protected Serializable pkVal() {
        return this.sysId;
    }
}
