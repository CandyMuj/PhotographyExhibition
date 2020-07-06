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
 * @FileName FooterInfo
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FooterInfo extends Model<FooterInfo> {
    @TableId
    @ApiModelProperty("页尾的链接和介绍相关的配置")
    private Long footerInfoId;
    @ApiModelProperty("关联页面的具体配置")
    private Long pageModelId;
    @ApiModelProperty("1可用 0不可用")
    private Integer enabled;
    @ApiModelProperty("文本 或者url;或者是json数据")
    private String footerData;


    @Override
    protected Serializable pkVal() {
        return this.footerInfoId;
    }
}
