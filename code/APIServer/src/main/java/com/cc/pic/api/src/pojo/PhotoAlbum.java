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
 * @FileName PhotoAlbum
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAlbum extends Model<PhotoAlbum> {
    @TableId
    @ApiModelProperty("相册")
    private Long photoAlbumId;
    @ApiModelProperty("创建人")
    private Long customerUserId;
    @ApiModelProperty("相册名")
    private String albumName;
    private Date addTime;
    @ApiModelProperty("相册描述")
    private String albumDesc;


    @Override
    protected Serializable pkVal() {
        return this.photoAlbumId;
    }
}
