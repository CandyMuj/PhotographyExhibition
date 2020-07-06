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
 * @FileName PhotoLibrary
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoLibrary extends Model<PhotoLibrary> {
    @TableId
    @ApiModelProperty("图片库")
    private Long photoLibraryId;
    @ApiModelProperty("逻辑删除时间")
    private Date deletedTime;
    @ApiModelProperty("图片描述")
    private String picDesc;
    @ApiModelProperty("图片链接")
    private String picUri;
    @ApiModelProperty("创建人")
    private Long customerUserId;
    @ApiModelProperty("逻辑删除")
    private Integer deleted;
    @ApiModelProperty("图片名称")
    private String picName;
    @ApiModelProperty("相册id 非必须")
    private Long photoAlbumId;
    @ApiModelProperty("新增时间")
    private Date addTime;


    @Override
    protected Serializable pkVal() {
        return this.photoLibraryId;
    }
}
