package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
    import java.util.Date;

/**
 * @ProjectName MyTest
 * @FileName PhotoAlbum
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAlbum extends Model<PhotoAlbum> {
    @TableId
    private Long photoAlbumId;
    private String albumName;
    private Date addTime;
    private String albumDesc;
    private Long customerUserId;


    @Override
    protected Serializable pkVal() {
        return this.photoAlbumId;
    }
}
