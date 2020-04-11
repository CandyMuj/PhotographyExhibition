package com.cc.pic.api.pojo.sys;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ProJectName APIServer
 * @FileName MapPoint
 * @Description 地图坐标类
 * @Author CandyMuj
 * @Date 2020/4/12 0:40
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class MapPoint {
    // 经度
    private double lon;
    // 纬度
    private double lat;

    private MapPoint() {
    }

}
