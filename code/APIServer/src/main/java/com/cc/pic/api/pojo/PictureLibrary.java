package com.cc.pic.api.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProJectName APIServer
 * @FileName PictureLibrary
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:04
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureLibrary extends Model<PictureLibrary> {
    @TableId
    private Integer pictureLibraryId;
    private Integer photoAlbumId;
    private String picName;
    private String picDesc;
    private String picUri;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
