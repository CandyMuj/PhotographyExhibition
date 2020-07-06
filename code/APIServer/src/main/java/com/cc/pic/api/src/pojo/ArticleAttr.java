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
 * @FileName ArticleAttr
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAttr extends Model<ArticleAttr> {
    @TableId
    @ApiModelProperty("文章属性关联表，如果有关联或属性此表才有对应的数据")
    private Long articleAttrId;
    @ApiModelProperty("文章ID")
    private Long articleId;
    @ApiModelProperty("扩展数据ID/如商品ID 没有就为0")
    private Long itemId;
    @ApiModelProperty("定位标识，方便前端定位做展示 文章中使用 <loc></loc> 标识")
    private String location;
    @ApiModelProperty("扩展数据类别/如商品-根据业务内容配置枚举 没有就为0")
    private Integer itemType;
    @ApiModelProperty("结合location确定位置")
    private Long orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.articleAttrId;
    }
}
