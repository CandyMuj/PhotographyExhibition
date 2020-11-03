package com.cc.pic.api.utils;

import com.cc.pic.api.pojo.MapPoint;

import java.math.BigDecimal;

/**
 * @ProJectName APIServer
 * @FileName MapUtil
 * @Description
 * @Author CandyMuj
 * @Date 2020/4/12 0:29
 * @Version 1.0
 */
public class MapUtil {

    private MapUtil() {
    }


    public static MapPoint getMapPoint(String lon, String lat) {
        return getMapPoint(Double.valueOf(lon), Double.valueOf(lat));
    }

    public static MapPoint getMapPoint(double lon, double lat) {
        return new MapPoint(lon, lat);
    }


    /**
     * 根据两个经纬度坐标计算距离
     * 获取两个地点之间的直线距离,精确到小数点后两位km
     * <p>
     * 算法1：更精确一点
     */
    public static BigDecimal getDistance(MapPoint start, MapPoint end) {
        double radLat1 = rad(start.getLat());
        double radLat2 = rad(end.getLat());

        double a = radLat1 - radLat2;
        double b = rad(start.getLon()) - rad(end.getLon());

        // 地球平均直径为12756km,半径为6378km
        // 6378.137赤道半径
        double earthR = 6378.137;

        double c = 2 * earthR * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));

        // c = Math.round(c * 10000d) / 10000d;

        return BigDecimal.valueOf(c).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static double getDistanceDouble(MapPoint start, MapPoint end) {
        return getDistance(start, end).doubleValue();
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 算法2
     *
     * @param start
     * @param end
     * @return
     */
    public static BigDecimal getDistence(MapPoint start, MapPoint end) {
        // 得到起点经纬度,并转换为角度
        double startLon = (Math.PI / 180) * start.getLon();
        double startLat = (Math.PI / 180) * start.getLat();
        // 得到终点经纬度,并转换为角度
        double endLon = (Math.PI / 180) * end.getLon();
        double endLat = (Math.PI / 180) * end.getLat();

        // 地球平均直径为12756km,半径为6378km
        double earthR = 6378.137;

        // 计算公式
        double distence = Math.acos(Math.sin(startLat) * Math.sin(endLat) + Math.cos(startLat) * Math.cos(endLat) * Math.cos(endLon - startLon)) * earthR;

        return BigDecimal.valueOf(distence).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static double getDistenceDouble(MapPoint start, MapPoint end) {
        return getDistence(start, end).doubleValue();
    }

}
