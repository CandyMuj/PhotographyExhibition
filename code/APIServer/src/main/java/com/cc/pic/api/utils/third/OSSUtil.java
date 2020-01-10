package com.cc.pic.api.utils.third;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cc.pic.api.exception.CandyException;
import com.cc.pic.api.utils.sys.YmlConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProJectName APIServer
 * @FileName OSSUtil
 * @Description 阿里云oss工具，支持多账号多bucket
 * @Author CandyMuj
 * @Date 2020/1/8 17:08
 * @Version 1.0
 */
public class OSSUtil {
    // 获取配置的前缀
    private static final String CONFIG_PREFIX = "third.aliyun.oss.";
    // 配置的后缀，及项目名;默认为default
    private String config_project = "default";

    // oss配置
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucketName;
    private boolean https;


    // oss客户端做缓存，以免每次都去建立连接
    // key：buildclient的三个参数按顺序以&分隔
    private static Map<String, OSS> clientMap = new HashMap<>();

    // 当前oss客户端
    private OSS ossClient;


    public OSSUtil() {
        initConfig();
    }

    public OSSUtil(String project) {
        if (StrUtil.isBlank(project)) {
            throw new CandyException("oss project name can not be empty!");
        }
        this.config_project = project;
        initConfig();
    }

    private void initConfig() {
        this.accessKeyId = YmlConfig.getString(CONFIG_PREFIX + config_project + ".accessKeyId");
        this.accessKeySecret = YmlConfig.getString(CONFIG_PREFIX + config_project + ".accessKeySecret");
        this.endpoint = YmlConfig.getString(CONFIG_PREFIX + config_project + ".endpoint");
        this.bucketName = YmlConfig.getString(CONFIG_PREFIX + config_project + ".bucketName");
        this.https = YmlConfig.getBooleanValue(CONFIG_PREFIX + config_project + ".https");

        if (StrUtil.isBlank(accessKeyId) || StrUtil.isBlank(accessKeySecret) || StrUtil.isBlank(endpoint) || StrUtil.isBlank(bucketName)) {
            throw new CandyException("init oss config failed has empty config!");
        }

        String clientKey = endpoint + "&" + accessKeyId + "&" + accessKeySecret;
        OSS ossClient = clientMap.get(clientKey);
        if (ossClient == null) {
            this.ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            clientMap.put(clientKey, this.ossClient);
        } else {
            this.ossClient = ossClient;
        }

        if (this.ossClient == null) {
            throw new CandyException("init oss client failed!");
        }
    }

    public String getBucketDomain() {
        String http = "http" + (https ? "s" : "") + "://";
        return http + bucketName + "." + endpoint + "/";
    }

}
