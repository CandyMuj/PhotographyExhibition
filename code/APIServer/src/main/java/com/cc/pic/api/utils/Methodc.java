package com.cc.pic.api.utils;

import com.cc.pic.api.config.Configc;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @ProJectName server
 * @FileName Methodc
 * @Description 本项目所需要的一些公用的方法 TODO
 * @Author CandyMuj
 * @Date 2019/5/27 14:06
 * @Version 1.0
 */
public class Methodc {

    public static List<String> array2list(String[] array) {
        return new ArrayList<>(Arrays.asList(array));
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
            while ((count = in.read(data, 0, data.length)) != -1){
                outStream.write(data, 0, count);
            }
            data = null;
            outStream.flush();
            return new String(outStream.toByteArray(), encode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
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

    public static String[] ListToArray(ArrayList<String> list) {
        return list != null && list.size() != 0 ? list.toArray(new String[list.size()]) : null;
    }

    /**
     * 将list转成字符串
     *
     * @param split 指定分隔符
     * @return
     */
    public static String ListToString(List<Integer> list, String split) {
        if (list == null) return null;
        StringBuilder str = new StringBuilder();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            str.append(iterator.next()).append(split);
        }

        if (str.length() > 0) str.deleteCharAt(str.length() - 1);

        return str.toString();
    }

    /**
     * 默认以逗号为分隔符
     */
    public static String ListToString(List<Integer> list) {
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

    public static void main(String[] s) {
//        System.out.println(dollar2cents(23.6566));
//        System.out.println(Mathc.noroundDouble(23.6566));

//        int[] arr = new int[]{1, 3, 2, 8, 3};
//        sortSelect(arr);

//        System.out.println(checkPhone("13666665555"));

    }

}
