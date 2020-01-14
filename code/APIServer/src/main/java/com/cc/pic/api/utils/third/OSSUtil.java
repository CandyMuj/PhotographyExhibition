package com.cc.pic.api.utils.third;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.cc.pic.api.enumc.OSSEnum;
import com.cc.pic.api.exception.CandyException;
import com.cc.pic.api.utils.sys.YmlConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String key, String original) {
        try {
            String fileName = key;
            int index = key.lastIndexOf("/");
            if (index != -1) {
                fileName = key.substring(index + 1);
            }

            if (metadata == null) {
                metadata = new ObjectMetadata();
            }
            // 设置自定义元数据
            Map<String, String> userMetadata = new HashMap<>();
            if (StrUtil.isNotBlank(original)) {
                fileName = original;
                // 真实文件名
                userMetadata.put("original", original);
            }
            if (userMetadata.size() > 0) {
                metadata.setUserMetadata(userMetadata);
            }

            metadata.setContentLength(inputStream.available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            // metadata.setContentType(MimeType.getContentType(Methodc.getFileExt(fileName)));
            metadata.setContentDisposition("inline;filename=" + fileName);


            PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream);
            request.setMetadata(metadata);
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

    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String key) {
        return uploadFile(inputStream, metadata, key, null);
    }

    public String uploadfile(InputStream inputStream, String key) {
        return uploadFile(inputStream, (ObjectMetadata) null, key, null);
    }

    /**
     * 使用默认的key生成策略
     *
     * @param inputStream
     * @return
     */
    public String uploadFile(InputStream inputStream, OSSEnum ossEnum, String prefix, String original) {
        String key = OSSEnum.buildKey(ossEnum != null ? ossEnum : OSSEnum.RANDOM_R, prefix, original);
        return uploadFile(inputStream, (ObjectMetadata) null, key, original);
    }

    public String uploadFile(InputStream inputStream, String original) {
        return uploadFile(inputStream, (OSSEnum) null, "file", original);
    }

    public String uploadImg(InputStream inputStream, String original) {
        return uploadFile(inputStream, (OSSEnum) null, "img", original);
    }

    /**
     * 文件前缀根目录根据文件的扩展名自动生成
     */
    public String uploadFileByext(InputStream inputStream, OSSEnum ossEnum, String original) {
        String key = OSSEnum.buildKeyByext(ossEnum != null ? ossEnum : OSSEnum.RANDOM_R, original);
        return uploadFile(inputStream, (ObjectMetadata) null, key, original);
    }

    public String uploadFileByext(InputStream inputStream, String original) {
        return uploadFileByext(inputStream, null, original);
    }


    /**
     * 获取对象
     */
    public InputStream getObject(String key) {
        return ossClient.getObject(bucketName, key).getObjectContent();
    }

    /**
     * 删除对象
     *
     * @param key
     * @return
     */
    public void deleteObject(String key) {
        ossClient.deleteObject(bucketName, key);
    }


    public static void main(String[] s) {
        new OSSUtil().uploadFile(new ByteArrayInputStream("Hello OSS!".getBytes()), "这是原文件名");
    }

}
