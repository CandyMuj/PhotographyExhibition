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
 * @FileName CustomerUser
 * @Description
 * @Author CandyMuj
 * @Date 2020/05/14 14:31
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUser extends Model<CustomerUser> {
    @TableId
    private Long customerUserId;
    private String userRealName;
    private Date addTime;
    private String userNickName;


    @Override
    protected Serializable pkVal() {
        return this.customerUserId;
    }
}
