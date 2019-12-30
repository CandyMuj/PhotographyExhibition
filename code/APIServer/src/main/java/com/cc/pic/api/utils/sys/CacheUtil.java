package com.cc.pic.api.utils.sys;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProJectName APIServer
 * @FileName CacheUtil
 * @Description 如果redis为空时，那么这个就是替代方案，使用本地内存做处理
 * @Author CandyMuj
 * @Date 2019/12/30 10:16
 * @Version 1.0
 */
public class CacheUtil {
    private static final long MINUTEUNIT = 1000;


    public static boolean HaveKey(String key) {
        return Data.list.containsKey(key);
    }

    // 获取缓存内容
    public static Object GetData(String key) {
        return Data.Get(key);
    }

    // 设置数据,如果KEY不存在则设置失败
    public static boolean SetData(String key, Object value) {
        return SetData(key, value, false);
    }

    // 设置数据，如果KEY不存在则新增
    public static boolean SetData(String key, Object value, boolean nullThenAdd) {
        if (Data.list.containsKey(key)) {
            Data.list.get(key).data = value;
            return true;
        } else if (nullThenAdd) {
            return AddData(key, value, true);
        }
        return false;
    }

    // 设置缓存
    // timeout=0永不过期，永不过期
    // 是否执行成功
    public static boolean AddData(String key, Object data) {
        return AddData(key, data, 0, false);
    }

    public static boolean AddData(String key, Object data, long time) {
        return AddData(key, data, time, false);
    }

    public static boolean AddData(String key, Object data, boolean force) {
        return AddData(key, data, 0, force);
    }

    // 设置缓存名，数据，超时时间（单位：秒，0永久不过期，force是否强制使用key）,ifill绑定接口
    public static boolean AddData(String key, Object data, long time, boolean force) {
        if (Data.list.containsKey(key) && !force)
            return false;
        if (time < 0)
            time = Math.abs(time);
        Data.list.put(key, new Data.DataItem(time, data));
        return true;
    }

    // 清除缓存
    public static boolean RemoveData(String key) {
        if (Data.list.containsKey(key)) {
            Data.list.remove(key);
            return true;
        }
        return false;
    }


    // -----------------------------------------------------


    private static class Data {
        private static Map<String, DataItem> list = new HashMap<>();

        private static Object Get(String key) {
            DataItem item = Data.list.get(key);
            if (item != null) {
                if (item.time > 0 && item.outTime < System.currentTimeMillis()) {
                    list.remove(key);
                    return null;
                }
                return item.data;
            }
            return null;
        }


        private static class DataItem {
            private Object data;
            private long outTime;
            private long time;

            // timeout（单位秒）
            // 0永不过期
            private DataItem(long time, Object data) {
                this.time = time;
                this.data = data;
                this.calcOutTime();
            }

            private void calcOutTime() {
                if (this.time > 0)
                    this.outTime = System.currentTimeMillis() + MINUTEUNIT * this.time;
            }
        }
    }

}
