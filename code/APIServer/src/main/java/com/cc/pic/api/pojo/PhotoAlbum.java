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
 * @FileName PhotoAlbum
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAlbum extends Model<PhotoAlbum> {
    @TableId
    private Integer photoAlbumId;
    private String albumName;
    private String albumDesc;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
