package com.cc.pic.api.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @ProJectName APIServer
 * @FileName User
 * @Description 此类中的数据，建议存放不可变动的数据，因为token是没有时效性的，如果用phone这些，那么并不能保证实时更新
 * 建议只使用userid，因为username可能是唯一的但必须要保证他后期不可变的，phone的话肯定不能用，因为哪怕他作为账号，手机号是无法限制不可变的
 * 所以谨慎使用除id外的数据，哪怕其他数据代表账号，也要保证这些数据不可变否则不建议使用
 * @Author CandyMuj
 * @Date 2019/12/24 14:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long userId;
    private String userName;
    private String phone;
    // 扩展数据
    private Map<String, Object> ext;

}
