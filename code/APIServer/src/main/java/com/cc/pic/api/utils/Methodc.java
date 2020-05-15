package com.cc.pic.api.utils;

import cn.hutool.core.util.StrUtil;
import com.cc.pic.api.config.Configc;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @ProJectName server
 * @FileName Methodc
 * @Description 本项目所需要的一些公用的方法
 * @Author CandyMuj
 * @Date 2019/5/27 14:06
 * @Version 1.0
 */
@Slf4j
public class Methodc {

    /**
     * 位运算
     */
    public static Integer getRole(Integer a, Integer b) {
        return a | b;
    }

    /**
     * 判断a是否有b的权限
     */
    public static boolean hasRole(Integer a, Integer b) {
        return ((a & b) == b);
    }

    /**
     * 根据排序字符串，返回排序sql
     *
     * @param orderbyList
     * @return
     */
    public static String getOrderbyStr(List<String> orderbyList) {
        String s = "";
        if (orderbyList != null && orderbyList.size() > 0) {
            s += " order by ";
            for (int i = 0; i < orderbyList.size(); i++) {
                if (i < orderbyList.size() - 1) {
                    s += orderbyList.get(i) + ",";
                } else {
                    s += orderbyList.get(i);
                }
            }
        }
        return s;
    }

    /**
     * 是否含有为空或空串的内容
     *
     * @param obj
     * @return true 有为空的
     */
    public static boolean hasBlank(Object... obj) {
        if (obj == null || obj.length <= 0) {
            return true;
        }

        for (Object o : obj) {
            if (o == null) {
                return true;
            }
            if (o instanceof Integer) {
                if ((Integer) o < 0) {
                    return true;
                }
            } else if (o instanceof String) {
                if (StrUtil.isBlank((String) o)) {
                    return true;
                }
            } else {
                throw new IllegalArgumentException("Unsupported");
            }
        }

        return false;
    }

    /**
     * 获取当前日历对象，修改小时和分钟
     *
     * @param hour
     * @param minute
     * @param zeroSecond 是否设置秒和毫秒为0 true:是
     * @return
     */
    public static Calendar setCalendar(int hour, int minute, boolean zeroSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        if (zeroSecond) {
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }

        return calendar;
    }

    /**
     * 获取日历对象 默认设置秒和毫秒为0
     *
     * @param hour
     * @param minute
     * @return
     */
    public static Calendar setCalendar(int hour, int minute) {
        return setCalendar(hour, minute, true);
    }

    /**
     * 判断bigdecimal是否是整数
     */
    public static boolean bigDecimalIsInteger(BigDecimal number) {
        if (!"".equals(number + "") && number != null) {
            return new BigDecimal(number.intValue()).compareTo(number) == 0;
        }

        throw new NullPointerException("BigDecimal 传入为空");
    }

    /**
     * 比较两个数组是否完全相等，含长度和内容，是实际的内容，要去重才行
     * <p>
     * 只有相同的类型比较才有意义，类型都不同那么肯定什么都不同了
     *
     * @param a
     * @param b
     * @return
     */
    public static <T> boolean containsArray(T[] a, T[] b) {
        // 去重
        TreeSet<T> aset = new TreeSet<>(Arrays.asList(a));
        TreeSet<T> bset = new TreeSet<>(Arrays.asList(b));

        // 长度比较
        if (aset.size() != bset.size()) return false;

        for (T item : aset) {
            if (!bset.contains(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 人民币转换：分转元
     *
     * @param cents
     * @return
     */
    public static double cents2dollar(Long cents) {
        return Mathc.divide(cents, 100);
    }

    /**
     * 人民币转换：元转分
     * 直接取小数后两位即精确到分（不进行四舍五入）
     *
     * @param dollar
     * @return
     */
    public static int dollar2cents(double dollar) {
        return (int) Mathc.multipy(Mathc.noroundDouble(dollar), 100);
    }

    public static String InputStream2String(InputStream in, String encode) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            byte[] data = new byte[4096];
            int count = -1;
            while ((count = in.read(data, 0, data.length)) != -1) {
                outStream.write(data, 0, count);
            }
            data = null;
            outStream.flush();
            return new String(outStream.toByteArray(), encode);
        } catch (Exception e) {
            log.error("Methodc Exception...", e);
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("Methodc Exception...", e);
            }
        }

        return null;
    }

    public static String InputStream2String(InputStream in) {
        return InputStream2String(in, Configc.GLOBAL_ENCODING);
    }

    /**
     * 数组选择排序  升序
     *
     * @param num
     */
    public static void sortSelect(int[] num) {
        int temp = 0;
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] > num[j]) {
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
    }

    /**
     * 数组逆序
     *
     * @param num
     */
    public static void reverse(int[] num) {
        int temp = 0;
        for (int min = 0, max = num.length - 1; min < max; min++, max--) {
            temp = num[min];
            num[min] = num[max];
            num[max] = temp;
        }
    }

    public static <T> List<T> array2list(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static String[] ListToArray(List<String> list) {
        return (list != null && list.size() > 0) ? list.toArray(new String[0]) : null;
    }

    /**
     * 将list转成字符串
     * <p>
     * 使用迭代器，不适用for，迭代器性能更佳
     *
     * @param split 指定分隔符
     * @return
     */
    public static String ListToString(List<?> list, String split) {
        if (list == null) return null;
        StringBuilder str = new StringBuilder();
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            str.append(iterator.next()).append(split);
        }

        if (str.length() > 0) str.deleteCharAt(str.length() - 1);

        return str.toString();
    }

    /**
     * 默认以逗号为分隔符
     */
    public static String ListToString(List<?> list) {
        return ListToString(list, ",");
    }

    /**
     * 校验国内手机号
     *
     * @param phone
     * @return true:正确
     */
    public static boolean checkPhone(String phone) {
        return Pattern.compile("0?(13|14|15|17|18|19)[0-9]{9}").matcher(phone).matches();
    }

    /**
     * 将java格式的字符串转换为html格式
     *
     * @param str
     * @return
     */
    public static String repJavaToHtml(String str) {
        if (str != null && !"".equals(str)) {
            str = str.replace("\r\n", "<br/>");
        }

        return str;
    }

    /**
     * 通过出生日期获取年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }

    /**
     * 处理富文本上传时空格显示异常的问题
     *
     * @param str
     * @return
     */
    public static String nbsp(String str) {
        return str.replace("%26", "&");
    }

    /**
     * 获取文件的后缀名（返回后缀 包含 '.')
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        int index = fileName.lastIndexOf(".");
        return index != -1 ? fileName.substring(index) : "";
    }


    public static void main(String[] s) {
//        System.out.println(dollar2cents(23.6566));
//        System.out.println(Mathc.noroundDouble(23.6566));

//        int[] arr = new int[]{1, 3, 2, 8, 3};
//        sortSelect(arr);

//        System.out.println(checkPhone("13666665555"));

    }

}
