package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProJectName APIServer
 * @FileName ThirdBind
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 16:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThirdBind extends Model<ThirdBind> {
    @TableId
    private Integer thirdBindId;
    private Integer customerId;
    private Integer secretType;
    private String secretKey;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
