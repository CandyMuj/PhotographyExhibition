package com.cc.pic.api.src.pojo.restpo;

import lombok.Data;

/**
 * @ProjectName api
 * @FileName OperatorInfo
 * @Description 查询运营商信息返回实体
 * @Author CandyMuj
 * @Date 2020/10/9 9:51
 * @Version 1.0
 */
@Data
public class OperatorInfo {
    // 手机号
    private String phone;
    // 省份
    private String province;
    // 运营商名称 如：中国移动
    private String ispName;
    // 区域id
    private String areaId;
    // 运营商id
    private String ispId;
    // 地区运营商名称 如：四川移动
    private String carrier;
    private String mts;
}
