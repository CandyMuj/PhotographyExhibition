package com.cc.pic.api.utils.third;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.cc.pic.api.exception.CandyException;
import com.cc.pic.api.utils.sys.YmlConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
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
@Slf4j
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

    /**
     * 根据默认前缀路径上传文件
     *
     * @return
     */
    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String key) {
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream);
            if (metadata != null) {
                request.setMetadata(metadata);
            }

            ossClient.putObject(request);
            log.info("Object：{} 存入OSS成功!", key);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String uploadFile(InputStream inputStream, String key, String original) {
        ObjectMetadata metadata = new ObjectMetadata();

        // 设置自定义元数据
        Map<String, String> userMetadata = new HashMap<>();
        if (StrUtil.isNotBlank(original)) {
            // 真实文件名
            userMetadata.put("original", original);
        }
        metadata.setUserMetadata(userMetadata);


        return uploadFile(inputStream, metadata, key);
    }

    public String uploadFile(InputStream inputStream, String key) {
        return uploadFile(inputStream, (ObjectMetadata) null, key);
    }


    public static void main(String[] s) {
        new OSSUtil().uploadFile(new ByteArrayInputStream("Hello OSS".getBytes()), "key1", "这是原文件名");
    }

}