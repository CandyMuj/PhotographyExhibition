package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * @ProjectName MyTest
 * @FileName Poster
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poster extends Model<Poster> {
    @TableId
    @ApiModelProperty("广告/banner")
    private Long posterId;
    @ApiModelProperty("封面")
    private String coverImg;
    @ApiModelProperty("关联附件类别，1链接 2文章")
    private Integer itemType;
    @ApiModelProperty("显示时间 发布以后在这个时间后才显示")
    private Date showTime;
    @ApiModelProperty("广告标题")
    private String title;
    @ApiModelProperty("是否启用")
    private Integer enabled;
    @ApiModelProperty("发布时间")
    private Date addTime;
    @ApiModelProperty("排序 越大排序越靠前")
    private Long orderIndex;
    @ApiModelProperty("url链接")
    private String itemUrl;
    @ApiModelProperty("业务实体ID，如果没有对象，则为0")
    private Long itemId;
    @ApiModelProperty("广告位置，dictid :adpostion")
    private Integer postion;


    @Override
    protected Serializable pkVal() {
        return this.posterId;
    }
}
