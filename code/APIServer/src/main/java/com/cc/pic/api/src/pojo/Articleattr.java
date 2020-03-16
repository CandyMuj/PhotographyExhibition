package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName Articleattr
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 14:37
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articleattr extends Model<Articleattr> {
    @TableId
    private Integer articleattrId;
    private Integer articleId;
    private Integer itemType;
    private Integer itemId;
    private String location;
    private Integer orderIndex;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
