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
 * @FileName CustomerThirdBind
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerThirdBind extends Model<CustomerThirdBind> {
    @TableId
    private Long customerThirdBindId;
    private Long customerId;
    private String secretKey;
    private Date addTime;
    private Integer secretType;


    @Override
    protected Serializable pkVal() {
        return this.customerThirdBindId;
    }
}
