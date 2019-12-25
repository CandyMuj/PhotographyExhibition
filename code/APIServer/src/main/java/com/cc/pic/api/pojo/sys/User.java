package com.cc.pic.api.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProJectName APIServer
 * @FileName User
 * @Description
 * @Author CandyMuj
 * @Date 2019/12/24 14:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private String phone;

}
