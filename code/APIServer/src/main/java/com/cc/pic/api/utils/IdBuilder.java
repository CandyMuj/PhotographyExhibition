package com.cc.pic.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*生成id*/
public class IdBuilder {
    private static int seed = 0;

    /**
     * 生成随机的id
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();

        int end2 = seed++; //random.nextInt(9999);
        if (seed > 9999)
            seed = 0;
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return new Long(str);
    }

    /**
     * 生成随机字符串
     *
     * @param onlyNumber 只包含数字
     * @param len        长度
     */
    public static String genRandomStr(boolean onlyNumber, int len) {
        String str = "";
        while (str.length() < len) {
            str += java.util.UUID.randomUUID();
            if (onlyNumber)
                str = str.replaceAll("[^\\d]", "");
        }
        return str.substring(0, len);
    }


    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return millis + String.format("%03d", end3);
    }


    /**
     * 验证码随机数
     *
     * @param min 1 max 999999
     */
    public static int getRandNum(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


    /**
     * 活动报名
     * 订单id生成
     * 首先，订单号有3个性质：1.唯一性  2.不可推测性 3.效率性
     * 唯一性和不可推测性不用说了，效率性是指不能频繁的去数据库查询以避免重复。
     * 况且满足这些条件的同时订单号还要足够的短。
     * 我在java下定制的订单号生成方式如下：
     * 订单ID
     * 目前规则来看，两个人在同一微秒提交订单重复的概率为1%
     *
     * @return
     */
    public static long orderId() {
        int r1 = (int) (Math.random() * (9) + 1);//产生2个0-9的随机数
        int r2 = (int) (Math.random() * (10));
        SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmss");
        String format = yyyyMMddHHmmss.format(new Date());
        return Long.parseLong(format + r1 + r2);
    }

    /**
     * 生成平台推广随即id
     */
    public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer();
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    /**
     * 这是典型的随机洗牌算法。
     * 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域）
     * 算法时间复杂度O(n)
     *
     * @return 随机8为不重复数组
     */
    public static String generateNumber() {
        String no = "";
        //初始化备选数组
        int[] defaultNums = new int[10];
        for (int i = 0; i < defaultNums.length; i++) {
            defaultNums[i] = i;
        }

        Random random = new Random();
        int[] nums = new int[LENGTH];
        //默认数组中可以选择的部分长度
        int canBeUsed = 10;
        //填充目标数组
        for (int i = 0; i < nums.length; i++) {
            //将随机选取的数字存入目标数组
            int index = random.nextInt(canBeUsed);
            nums[i] = defaultNums[index];
            //将已用过的数字扔到备选数组最后，并减小可选区域
            swap(index, canBeUsed - 1, defaultNums);
            canBeUsed--;
        }
        if (nums.length > 0) {
            for (int i = 1; i < nums.length; i++) {
                no += nums[i];
            }
        }

        return no;
    }

    private static final int LENGTH = 8;

    private static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list.add(genRandomNum(16));
//            for (int j = 0; j < list.size(); j++) {
//                if (genRandomNum(16).equals(list.get(j))) {
//                    System.out.println("重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复重复");
//                }
//            }
//        }
//        System.out.println(genRandomNum(16));

        System.out.println(buildNum());

    }


    /**
     * 生成唯一的编号（纯数字）
     * - 最小为9位（时间戳）
     * - 默认为18位
     *
     * @return
     */
    public static String buildNum() {
        return buildNum(9);
    }

    public static String buildNum(int len) {
        if (len < 0) {
            len = 0;
        }
        return Long.toString(new Date().getTime()).substring(4) + IdBuilder.genRandomStr(true, len);
    }

}
