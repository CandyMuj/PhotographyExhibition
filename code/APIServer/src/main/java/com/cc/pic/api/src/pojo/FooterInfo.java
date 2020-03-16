package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ProJectName APIServer
 * @FileName FooterInfo
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FooterInfo extends Model<FooterInfo> {
    @TableId
    private Integer footerInfoId;
    private Integer footerType;
    private String content;
    private Integer enable;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
