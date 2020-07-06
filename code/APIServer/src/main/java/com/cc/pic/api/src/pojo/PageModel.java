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
 * @FileName PageModel
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel extends Model<PageModel> {
    @TableId
    @ApiModelProperty("页面模块配置")
    private Long pageModelId;
    @ApiModelProperty("标题/模块名")
    private String modelName;
    @ApiModelProperty("描述")
    private String modelDesc;
    @ApiModelProperty("扩展数据，推荐使用json格式；如：about中的图片")
    private String extData;
    @ApiModelProperty("按钮配置 非必须，若为空则没有按钮 （格式：{text:按钮文字,key:关联类型,val:关联值}")
    private String btnConfig;
    @ApiModelProperty("模块类型 单行轮播图 多行展示 about 文章")
    private Integer modelType;
    @ApiModelProperty("排序 越大越靠前")
    private Long orderIndex;
    @ApiModelProperty("如果存在多级的话那么这里就有值")
    private Long pid;


    @Override
    protected Serializable pkVal() {
        return this.pageModelId;
    }
}
