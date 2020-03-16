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
 * @FileName Customer
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/6 15:05
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Model<Customer> {
    @TableId
    private Integer customerId;
    private String account;
    private String password;
    private String nickName;
    private String avatar;
    private Integer role;
    private Integer sex;
    private Integer enable;
    private Date addTime;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
