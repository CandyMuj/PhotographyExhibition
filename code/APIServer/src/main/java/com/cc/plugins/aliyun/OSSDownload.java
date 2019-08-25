package com.cc.plugins.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProJectName server
 * @FileName OSSDownload
 * @Description TODO
 * @Author CandyMuj
 * @Date 2019/6/18 15:04
 * @Version 1.0
 */
public class OSSDownload {

    // oss桶的名称
    private static final String bucketName = "hxxx";

    // 本地保存地址
    private static final String localPath = "D:\\IDEA\\Projects\\weilai9\\shuicheche\\file\\";

    public static void main(String[] ss) {
        // todo 这三个参数通过阿里云获取，并修改
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAIQaNHXxxxxx";
        String accessKeySecret = "FvCTcRC4HrlV0cQcXxxxxx";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        getImgList(ossClient);

//        dow(ossClient, "img/2019-06/1560756877896.png/120");

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static List<String> getImgList(OSSClient ossClient) {
        List<String> list = new ArrayList<>();
        for (String ossObjName : list) {
            dow(ossClient, ossObjName);
        }
        return list;
    }

    /**
     * 核心方法，用于下载
     *
     * @param ossClient
     * @param objectName
     */
    public static void dow(OSSClient ossClient, String objectName) {
        try {
            if (objectName.indexOf("/120") != -1) {
                objectName = objectName.substring(0, objectName.indexOf("/120"));
            }

            // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
            File file = new File(localPath + objectName);
            File pfile = file.getParentFile();
            if (!pfile.exists()) {
                pfile.mkdirs();
            }
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), file);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - \t" + objectName);
        }
    }

}
