package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName PhotographyExhibition
 * @FileName PageModel
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageModel extends Model<PageModel> {
    @TableId
    private Long pageModelId;
    private Long pid;
    private Integer modelType;
    private String btnConfig;
    private String extData;
    private String modelName;
    private Long orderIndex;
    private String modelDesc;


    @Override
    protected Serializable pkVal() {
        return this.pageModelId;
    }
}
