package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName LaiDianPay
 * @FileName SystemLog
 * @Description
 * @Author CandyMuj
 * @Date 2020/09/30 14:58
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemLog extends Model<SystemLog> {
    @ApiModelProperty("描述")
    private String describe;
    @ApiModelProperty("接口地址")
    private String restUrl;
    @ApiModelProperty("日志时间")
    private Date addTime;
    @ApiModelProperty("用户id 如果是管理系统的操作则有此记录")
    private Long userAccountId;
    @ApiModelProperty("接口参数 json格式")
    private String restParam;
    @ApiModelProperty("如果是修改或者删除操作 记录修改前的状态 json格式")
    private String oldParam;
    @ApiModelProperty("日志类型 枚举值：外部接口，系统操作")
    private Integer logType;
    @ApiModelProperty("操作结果")
    private String result;
    @ApiModelProperty("操作ip")
    private String ip;


    @Override
    protected Serializable pkVal() {
        return null;
    }
}
