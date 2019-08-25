package com.cc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @FileName Mathc
 * @Author CandyMuj
 * @Date 2019/4/4 13:00
 * @Version 1.0
 */
public class Mathc {

    // ------------------- double小数防止精度丢失问题的计算处理方法

    private static final int ADD = 1;
    private static final int SUBSTRACT = 2;
    private static final int MULTIPY = 3;
    private static final int DIVIDE = 4;
    private static final int ABS = 5;

    /**
     * 加
     *
     * @param val
     * @return
     */
    public static double add(Object... val) {
        return comm(ADD, val);
    }

    /**
     * 减
     *
     * @param val
     * @return
     */
    public static double substract(Object... val) {
        return comm(SUBSTRACT, val);
    }

    /**
     * 乘
     *
     * @param val
     * @return
     */
    public static double multipy(Object... val) {
        return comm(MULTIPY, val);
    }

    /**
     * 除
     *
     * @param val
     * @return
     */
    public static double divide(Object... val) {
        return comm(DIVIDE, val);
    }

    /**
     * 绝对值
     *
     * @param val
     * @return
     */
    public static double abs(Object val) {
        return new BigDecimal("" + val).abs().doubleValue();
    }

    private static double comm(int type, Object... val) {
        if (val == null || val.length <= 0)
            return 0;

        BigDecimal bigDecimal = new BigDecimal("" + val[0]);
        for (int i = 1; i < val.length; i++) {
            BigDecimal bb = new BigDecimal("" + val[i]);
            switch (type) {
            case ADD:
                bigDecimal = bigDecimal.add(bb);
                break;
            case SUBSTRACT:
                bigDecimal = bigDecimal.subtract(bb);
                break;
            case MULTIPY:
                bigDecimal = bigDecimal.multiply(bb);
                break;
            case DIVIDE:
                bigDecimal = bigDecimal.divide(bb);
                break;
            }
        }

        return bigDecimal.doubleValue();
    }

    // ------------------- 小数的处理（取多少位小数，是否四舍五入）

    /**
     * 四舍五入处理小数（保留小数）
     *
     * @param d
     * @param bit
     * @return
     */
    public static double roundDouble(double d, int bit) {
        return formatDouble(d, bit, RoundingMode.HALF_UP);
    }

    /**
     * 不四舍五入处理小数（保留小数）
     *
     * @param d
     * @param bit
     * @return
     */
    public static double noroundDouble(double d, int bit) {
        return formatDouble(d, bit, RoundingMode.DOWN);
    }

    /**
     * 默认两位小数
     *
     * @param d
     * @return
     */
    public static double roundDouble(double d) {
        return formatDouble(d, 2, RoundingMode.HALF_UP);
    }

    /**
     * 默认两位小数
     *
     * @param d
     * @return
     */
    public static double noroundDouble(double d) {
        return formatDouble(d, 2, RoundingMode.DOWN);
    }

    /**
     * 将小数直接截取两位，不做任何处理 小数截取两位进行四舍五入 - 可指定获取小数位数
     *
     * @param d
     * @return
     */
    private static double formatDouble(double d, int bit, RoundingMode roundingMode) {
        return new BigDecimal(d + "").setScale(bit, roundingMode).doubleValue();
    }

    /**
     * 两个整型相除 四舍五入
     * 
     * @param num1
     * @param num2
     * @return
     */
    public static int intDivide(int num1, int num2) {
        if (num2 == 0) {
            return 0;
        }
        return Math.round(num1 / (num2 * 1.0f));
    }
}
