package com.cc.pic.api.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @ProJectName APIServer
 * @FileName MimeType
 * @Description 关于mime类型的转换
 * @Author CandyMuj
 * @Date 2020/1/13 15:49
 * @Version 1.0
 */
public class MimeType {
    // mime 配置文件路径
    private static final String CONFIG_FILE = "other/mime.json";


    private static JSONObject CONFIG_JSON;

    static {
        // 初始化json配置文件，转为json对象
        if (CONFIG_JSON == null) {
            CONFIG_JSON = JSONObject.parseObject(Methodc.InputStream2String(MimeType.class.getClassLoader().getResourceAsStream(CONFIG_FILE)));
        }
    }


    /**
     * 传入的参数可以加 '.' 也可以不加 '.'
     * 根据文件后缀获取contentType;mime类型
     * 有些文件的确实没有后缀，所以如果后缀为空就是用默认类型
     *
     * @param fileExt
     * @return
     */
    public static String getContentType(String fileExt) {
        String mime = null;
        if (StrUtil.isNotBlank(fileExt)) {
            fileExt = fileExt.replaceFirst(".", "").toLowerCase();
            mime = CONFIG_JSON.getString(fileExt);
        }

        return mime != null ? mime : "application/octet-stream";
    }

}
