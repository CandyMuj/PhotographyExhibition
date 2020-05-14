package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @ProjectName MyTest
 * @FileName FooterInfo
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FooterInfo extends Model<FooterInfo> {
    @TableId
    private Long footerInfoId;
    private Integer enabled;
    private String footerData;
    private Long pageModelId;


    @Override
    protected Serializable pkVal() {
        return this.footerInfoId;
    }
}
