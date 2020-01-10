package com.cc.pic.api.utils.third;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cc.pic.api.exception.CandyException;
import com.cc.pic.api.utils.Methodc;

/**
 * @ProJectName APIServer
 * @FileName UeditorUtil
 * @Description 百度富文本工具类
 * @Author CandyMuj
 * @Date 2020/1/9 16:27
 * @Version 1.0
 */
public class UeditorUtil {
    // 配置文件的路径,一个模板配置，不包含url前缀的
    private static final String CONFIG_FILE = "third/ueditor_config.json";


    // 基本的配置 不包含url前缀的;用于缓存，不用每次都去读一次配置文件
    private static JSONObject CONFIG_JSON;
    private String url_prefix;

    static {
        // 初始化json配置文件，转为json对象
        if (CONFIG_JSON == null) {
            CONFIG_JSON = JSONObject.parseObject(Methodc.InputStream2String(UeditorUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE)));
        }
    }

    private UeditorUtil() {
    }

    private UeditorUtil(String urlPrefix) {
        if (StrUtil.isBlank(urlPrefix)) {
            throw new CandyException("urlPrefix can not empty!");
        }
        this.url_prefix = (urlPrefix.charAt(urlPrefix.length() - 1) == '/') ? urlPrefix : urlPrefix + "/";
    }

    /**
     * 获取oss的默认bucket中的配置
     *
     * @return
     */
    public static UeditorUtil getInstance() {
        return new UeditorUtil(new OSSUtil().getBucketDomain());
    }

    /**
     * 通过自定义的oss project 中的配置获取实例
     *
     * @param project
     * @return
     */
    public static UeditorUtil getInstance(String project) {
        return new UeditorUtil(new OSSUtil(project).getBucketDomain());
    }

    /**
     * 自定义前缀，格式：http://xxx.xxx.xx/
     * 需要包含协议头以及最后的‘/’
     *
     * @return
     */
    public static UeditorUtil getInstanceByCustom(String urlPrefix) {
        return new UeditorUtil(urlPrefix);
    }


    public JSONObject getConfig() {
        JSONObject jobj = new JSONObject(CONFIG_JSON);
        jobj.put("imageUrlPrefix", url_prefix);
        return jobj;
    }

    public String getConfigStr() {
        return getConfig().toJSONString();
    }

}
