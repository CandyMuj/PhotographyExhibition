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
 * @FileName FileList
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileList extends Model<FileList> {
    @TableId
    @ApiModelProperty("文件统一管理库，如果是为了速度，可使用redis，但是总感觉redis不太可靠")
    private Long fileListId;
    @ApiModelProperty("文件关联业务类型")
    private Integer itemType;
    @ApiModelProperty("逻辑删除时间")
    private Date deletedTime;
    @ApiModelProperty("保存地址")
    private String savedUri;
    @ApiModelProperty("业务id")
    private Long itemId;
    @ApiModelProperty("逻辑删除")
    private Integer deleted;
    @ApiModelProperty("添加时间")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.fileListId;
    }
}
