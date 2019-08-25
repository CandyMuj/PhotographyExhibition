package com.cc.utils.secret;

import java.security.MessageDigest;

public class MD5Util {
    /***
     * MD5加密 生成32位md5码
     * 
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    // 对MD5算法简要的叙述可以为：MD5以512位分组来处理输入的信息，且每一分组又被划分为16个32位子分组，经过了一系列的处理后，算法的输出由四个32位分组组成，将这四个32位分组级联后将生成一个128位散列值。
    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 测试主函数
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("000000");
        System.out.println("原始：" + str);
        System.out.println("MD5后：" + md5Encode(str));
    }
}
