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
 * @FileName Article
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article extends Model<Article> {
    @TableId
    @ApiModelProperty("文章表")
    private Long articleId;
    @ApiModelProperty("封面")
    private String coverImg;
    @ApiModelProperty("文章二级类别 dictcode:sysarticle_type 仅系统文章有/ 帮助反馈，关于我们...")
    private Integer articleType2;
    @ApiModelProperty("发布人用户id")
    private Long customerId;
    @ApiModelProperty("阅读次数")
    private Integer readNum;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("文章类型 1系统文章，系统内部使用，外部不可编辑(和广告表进行关联的，在添加广告时自动添加一篇文章）")
    private Integer articleType;
    @ApiModelProperty("是否启用/展示")
    private Integer enabled;
    @ApiModelProperty("新增时间")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.articleId;
    }
}
