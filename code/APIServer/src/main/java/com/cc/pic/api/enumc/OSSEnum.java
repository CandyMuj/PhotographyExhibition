package com.cc.pic.api.enumc;

import com.cc.pic.api.utils.Methodc;

import java.util.UUID;

/**
 * @ProJectName APIServer
 * @FileName OSSEnum
 * @Description
 * @Author CandyMuj
 * @Date 2020/1/10 11:08
 * @Version 1.0
 */
public enum OSSEnum {
    /**
     * 本地命名
     */
    LOCAL(1, "本地命名"),
    /**
     * 随机命名
     */
    RANDOM(2, "随机命名 保留原文件名，在其基础上加上一串随机值"),
    /**
     * 默认
     * 完全随机的命名
     */
    RANDOM_R(3, "随机命名 不保留原文件名，直接是一串随机的"),
    /**
     * 时间戳命名
     */
    TIMESTAMP(4, "时间戳命名"),
    /**
     * UUID-时间戳命名
     */
    UUID_TIMESTAMP(5, "UUID-时间戳命名");

    private int value;
    private String text;

    OSSEnum(int value, String text) {
        this.value = value;
    }


    /**
     * 根据上传文件命名类型生成上传到oss的文件key
     *
     * @param type   OSSEnum  枚举
     * @param source 原始文件名
     */
    public static String buildFileName(OSSEnum type, String source) throws Exception {
        String fileName = source;
        String fileExt = Methodc.getFileExt(source).toLowerCase();
        switch (type) {
            case LOCAL:
                return fileName;
            case RANDOM:
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                int len = 5;
                String chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
                int maxPos = chars.length();
                for (int i = 0; i < len; i++) {
                    fileName += chars.charAt((int) Math.floor(Math.random() * maxPos));
                }
                break;
            case RANDOM_R:
                fileName = "";
                int len1 = 10;
                String chars1 = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
                int maxPos1 = chars1.length();
                for (int i = 0; i < len1; i++) {
                    fileName += chars1.charAt((int) Math.floor(Math.random() * maxPos1));
                }
                break;
            case TIMESTAMP:
                fileName = String.valueOf(System.currentTimeMillis());
                break;
            case UUID_TIMESTAMP:
                fileName = UUID.randomUUID().toString().replace("-", "")
                        .toLowerCase()
                        + String.valueOf(System.currentTimeMillis());
                break;
            default:
                return fileName;
        }
        fileName += fileExt;
        return fileName;
    }

}
