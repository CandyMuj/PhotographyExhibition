package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName MyTest
 * @FileName Dict
 * @Description
 * @Author CandyMuj
 * @Date 2020/07/06 17:28
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dict extends Model<Dict> {
    @TableId
    @ApiModelProperty("字典表")
    private Long dictId;
    @ApiModelProperty("字典父级id")
    private Long dictPid;
    @ApiModelProperty("自身限制，一般仅根节点可自选;可读可写，只读，不可新增")
    private Integer selfLimit;
    @ApiModelProperty("子级限制")
    private Integer childrenLimit;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("扩展数据 推荐使用json格式数据")
    private String extData;
    @ApiModelProperty("字典名称")
    private String dictName;
    @ApiModelProperty("字典编码")
    private String dictCode;
    @ApiModelProperty("字典类型；即与根节点code保持一致")
    private String dictType;
    @ApiModelProperty("排序-越大越靠前")
    private Long orderIndex;


    @Override
    protected Serializable pkVal() {
        return this.dictId;
    }
}
