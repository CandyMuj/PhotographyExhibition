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
 * @FileName FileList
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileList extends Model<FileList> {
    @TableId
    private Long fileListId;
    private Long itemId;
    private Integer deleted;
    private String savedUri;
    private Date deletedTime;
    private Date addTime;
    private Integer itemType;


    @Override
    protected Serializable pkVal() {
        return this.fileListId;
    }
}
