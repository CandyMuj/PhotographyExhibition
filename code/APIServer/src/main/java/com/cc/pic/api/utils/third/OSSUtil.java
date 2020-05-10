package com.cc.pic.api.utils.third;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.cc.pic.api.enumc.OSSEnum;
import com.cc.pic.api.exception.CandyException;
import com.cc.pic.api.utils.sys.YmlConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProJectName APIServer
 * @FileName OSSUtil
 * @Description 阿里云oss工具，支持多账号多bucket
 * @Author CandyMuj
 * @Date 2020/1/8 17:08
 * @Version 1.0
 * <p>
 * 不建议再对此类上传文件相关方法进行扩展！！！ 若有其他类型的方法可在uploadFileByext 后方继续扩展。
 * 为什么上传文件不建议再做扩展？
 * 剩下的基本都是不常用的所以我也就难得写了，很多杂七杂八的方法
 * 1、比如根据传入文件名不做处理直接存不常用，根据file对象自己转流再传就行了
 * 2、如果是根据链接（字符串）
 * 3、那么就又会有根据url（对象），也完全可以自己外面处理了转成流即可
 * 如果出现上诉情况，就又要把所有的上传方法都重载一次，那样就会很麻烦，而且还可能有很多我没考虑到的类型，那样就更多了，没必要的
 * 我不可能把所有的情况都写出来，包括官方设计框架的都是，把基础的写出来，更多的都是你自己去扩展调用底层的基础法方法自己处理逻辑，如果什么都给你封装了那还要你开发人员什么事？
 * 都是自定义扩展
 * <p>
 * 在此我把底层方法都是开放出来的，实在觉得提供的方法不够用自己在业务层自定义处理调用底层基础方法即可  ！！！
 */
@Slf4j
public class OSSUtil {
    // 上传签证超时时间
    private final static long expireTime = 1800;
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


            PutObjectRequest request = new PutObjectRequest(bucketName, key, inputStream, metadata);
            // request.setMetadata(metadata);
            ossClient.putObject(request);
            log.info("Object：{} 存入OSS成功!", key);
            return key;
        } catch (Exception e) {
            log.error("OSSUtil Exception...", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                log.error("OSSUtil Exception...", e);
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
     * 获取文件上传签证
     * 通过前端上传文件的时候需要用到，但是一般不会使用
     */
    public Map<String, String> uploadFilePolicy() {
        try {
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);

            // 这里的filename随便输一个不含有后缀的字符串即可，目的是为了绕过方法内的非空校验（因为不想写重载方法了太麻烦了，这个方法就随便写一下了）
            // 因为获取签名的时候还没有选择文件，所以这里不会有后缀产生，后缀需要前端选择文件后再拼接
            String key = OSSEnum.buildKey(OSSEnum.RANDOM_R, "policy", "x");
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, key.substring(0, key.lastIndexOf("/")));

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("accessid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("host", this.getBucketDomain());
            respMap.put("key", key);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;
        } catch (Exception e) {
            log.error("OSSUtil Exception...", e);
        }

        return null;
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
