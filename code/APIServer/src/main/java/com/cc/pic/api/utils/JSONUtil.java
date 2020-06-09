package com.cc.pic.api.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cc.pic.api.config.Configc;

/**
 * @ProjectName api
 * @FileName JSONUtil
 * @Description 进一步封装ali的json对象方法扩展
 * @Author CandyMuj
 * @Date 2020/6/9 13:28
 * @Version 1.0
 */
public class JSONUtil {

    private static JSONObject parseObject(Object object, boolean formatDate, String dateFormat) {
        if (formatDate) {
            if (StrUtil.isBlank(dateFormat)) {
                dateFormat = Configc.DEFAULT_DATEFORMAT;
            }

            return JSONObject.parseObject(JSONObject.toJSONStringWithDateFormat(object, dateFormat));
        } else {
            return JSONObject.parseObject(JSONObject.toJSONString(object));
        }
    }

    private static JSONArray parseArray(Object object, boolean formatDate, String dateFormat) {
        if (formatDate) {
            if (StrUtil.isBlank(dateFormat)) {
                dateFormat = Configc.DEFAULT_DATEFORMAT;
            }

            return JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(object, dateFormat));
        } else {
            return JSONArray.parseArray(JSONArray.toJSONString(object));
        }
    }


    public static JSONObject parseObject(Object object) {
        return parseObject(object, false, null);
    }

    public static JSONObject parseObjectWithDateFormat(Object object, String dateFormat) {
        return parseObject(object, true, dateFormat);
    }

    public static JSONObject parseObjectWithDateFormat(Object object) {
        return parseObjectWithDateFormat(object, null);
    }


    public static JSONArray parseArray(Object object) {
        return parseArray(object, false, null);
    }

    public static JSONArray parseArrayWithDateFormat(Object object, String dateFormat) {
        return parseArray(object, true, dateFormat);
    }

    public static JSONArray parseArrayWithDateFormat(Object object) {
        return parseArrayWithDateFormat(object, null);
    }

}
