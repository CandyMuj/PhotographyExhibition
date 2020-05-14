package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
    import java.util.Date;

/**
 * @ProjectName PhotographyExhibition
 * @FileName PhotoLibrary
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoLibrary extends Model<PhotoLibrary> {
    @TableId
    private Long photoLibraryId;
    private Integer deleted;
    private String picDesc;
    private String picUri;
    private Date deletedTime;
    private String picName;
    private Date addTime;
    private Long photoAlbumId;
    private Long customerUserId;


    @Override
    protected Serializable pkVal() {
        return this.photoLibraryId;
    }
}
