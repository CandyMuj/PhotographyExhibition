package com.cc.pic.api.enumc;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.utils.Methodc;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ProJectName APIServer
 * @FileName OSSEnum
 * @Description 一般来说，不会使用传入的源文件名直接存，一般都是处理过的
 * 因为你不能保证分级后的同一个目录不存在重复的文件，多个用户可能上传的文件名就是一样的，文件类型或者内容可能都不一样
 * 除非一个文件一个文件夹，文件夹使用随机的生成规则生成，但是那样就很low
 * @Author CandyMuj
 * @Date 2020/1/10 11:08
 * @Version 1.0
 */
@Slf4j
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
    UID(5, "uuid 不去除-"),
    UID_ID(6, "去除-"),
    UID_TIMESTAMP(7, "UUID-时间戳命名 不去除-"),
    UID_IDTIMESTAMP(8, "UUID-时间戳命名 去除-");

    private int value;
    private String text;

    OSSEnum(int value, String text) {
        this.value = value;
    }


    /**
     * 没有前缀和策略，直接生成传入策略的新文件名（相当于在根目录下生成新的文件名）
     * <p>
     * 根据上传文件命名类型生成上传到oss的文件key
     *
     * @param type     OSSEnum  枚举
     * @param fileName 原始文件名
     */
    public static String buildKey(OSSEnum type, String fileName) {
        if (StrUtil.isBlank(fileName)) return null;
        if (type == null) return fileName;

        String fileExt = Methodc.getFileExt(fileName).toLowerCase();
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
            case UID:
                fileName = UUID.randomUUID().toString();
                break;
            case UID_ID:
                fileName = UUID.randomUUID().toString().replace("-", "");
                break;
            case UID_TIMESTAMP:
                fileName = UUID.randomUUID().toString()
                        + String.valueOf(System.currentTimeMillis());
                break;
            case UID_IDTIMESTAMP:
                fileName = UUID.randomUUID().toString().replace("-", "")
                        + String.valueOf(System.currentTimeMillis());
                break;
            default:
                return fileName;
        }
        fileName += fileExt;
        return fileName;
    }

    /**
     * 没有前缀和策略，直接是传入的文件名（相当于在根目录下存放传入的文件名）
     *
     * @param fileName
     * @return
     */
    public static String buildKey(String fileName) {
        return buildKey((OSSEnum) null, fileName);
    }

    /**
     * 前缀+（目录处理策略）+根据传入的策略生成新的文件名
     * <p>
     * 后缀末尾无需加 '/' 我会自动加,当然加也不会报错，我会处理，只是方便传值罢了
     */
    public static String buildKey(OSSEnum type, String prefix, String fileName) {
        StringBuilder key = new StringBuilder();

        if (StrUtil.isNotBlank(prefix)) {
            char first = prefix.charAt(0);
            if (first == '/' || first == '\\') {
                log.error("不能以“/”或者“\\”字符开头。");
                return null;
            }
            key.append(prefix).append(prefix.lastIndexOf("/") != prefix.length() - 1 ? "/" : "");
        }

        // 目录处理策略 这是必须的，无论是否有前缀，这个都必须有，不允许所有文件全部扔在一个目录下
        key.append(new SimpleDateFormat("yyyy-MM").format(new Date())).append("/");

        if (type != null) {
            key.append(buildKey(type, fileName));
        } else {
            key.append(fileName);
        }

        return key.toString();
    }

    /**
     * 前缀+（目录处理策略）+传入的文件名
     *
     * @param prefix
     * @param fileName
     * @return
     */
    public static String buildKey(String prefix, String fileName) {
        return buildKey(null, prefix, fileName);
    }

    /**
     * 无前缀 （目录处理策略）+根据传入的策略生成新的文件名（相当于在根目录根据目录策略生成目录再生成新的文件名）
     *
     * @param type
     * @param fileName
     * @return
     */
    public static String buildkey(OSSEnum type, String fileName) {
        return buildKey(type, null, fileName);
    }

    /**
     * 无前缀 （目录处理策略）+传入的文件名（相当于在根目录根据目录策略生成目录再根据传入的文件名）
     *
     * @param fileName
     * @return
     */
    public static String buildkey(String fileName) {
        return buildKey((String) null, fileName);
    }

    /**
     * 前缀+（目录处理策略）+根据传入的策略生成新的文件名
     * <p>
     * 前缀是根据文件后缀自动生成的
     *
     * @param fileName
     * @return
     */
    public static String buildKeyByext(OSSEnum type, String fileName) {
        String fileExt = Methodc.getFileExt(fileName).toLowerCase();
        if (StrUtil.isNotBlank(fileExt)) {
            fileExt = fileExt.replaceFirst(".", "");
        } else {
            fileExt = "unknown";
        }

        return buildKey(type, fileExt, fileName);
    }

    /**
     * 前缀+（目录处理策略）+传入的文件名
     * <p>
     * 前缀是根据文件后缀自动生成的
     *
     * @param fileName
     * @return
     */
    public static String buildKeyByext(String fileName) {
        return buildKeyByext(null, fileName);
    }

}
