package com.cc.pic.api.src.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;

/**
 * @ProjectName PhotographyExhibition
 * @FileName Customer
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Model<Customer> {
    @TableId
    private Long customerId;
    private Integer frozen;
    private Date frozenTime;
    private String frozenReason;
    private String passwd;
    private String account;
    private Integer userType;
    private Date addTime;
    private Date lastLoginTime;


    @Override
    protected Serializable pkVal() {
        return this.customerId;
    }
}
