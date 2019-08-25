package com.cc.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @FileName Methodc
 * @Author CandyMuj
 * @Date 2019/5/27 14:06
 * @Version 1.0
 */
public class Methodc {

    public static ArrayList<String> array2list(String[] array) {
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
    // public static double cents2dollar(int cents) {
    // return Mathc.divide(cents, 100);
    // }

    /**
     * 人民币转换：元转分 直接取小数后两位即精确到分（不进行四舍五入）
     *
     * @param dollar
     * @return
     */
    public static int dollar2cents(double dollar) {
        return (int) Mathc.multipy(Mathc.noroundDouble(dollar), 100);
    }

    public static String InputStreamTOString(InputStream in) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return new String(outStream.toByteArray(), "ISO-8859-1");
    }

    /**
     * 数组选择排序 升序
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

    public static Object[] listToArray(ArrayList<Object> list) {
        return list != null && list.size() != 0 ? list.toArray(new Object[list.size()]) : null;
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
     * @return
     */
    public static boolean checkPhone(String phone) {
        return Pattern.compile("0?(13|14|15|17|18|19)[0-9]{9}").matcher(phone).matches();
    }

    /**
     * date2 比 date1 多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  // 同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  // 闰年
                    timeDistance += 366;
                } else {  // 不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {  // 不同年
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 传入一个以逗号分隔的字符串，返回其中分隔的内容的随机值，且这些值不会重复
     * <p>
     * 这里为什么要使用两个循环，如果只是实现这种返回的结果是去重的，完全可以用一个循环就解决
     * Set<String> set = new HashSet<>();
     * do {
     * set.add(list.get((int) (Math.random() * llen)));
     * } while (set.size() < len);
     * <p>
     * 将 rIndex 替换为上述的循环，并把第二个循环删除即可
     *
     * <p>
     * 但是可能会出现三种随机场景：
     * 1、对结果去重
     * 2、对结果不去重（同一个值可能出现n次）
     * 3、结果可以重复，但是重复的最大的可能次数为传入字符串中重复的次数
     * <p>
     * 前两种方法使用两个和使用一个循环结果一致，且使用一个循环应该还要快一点，当应对第三种场景的时候就只能使用两个循环才能处理
     * 因为值不可去重，index需要去重，才能保证为最大重复数，如果使用一个循环，那么只能单纯的对结果值进行去重或不去重（即第一二种场景）
     * 而第三种场景只能通过对初始的值不处理重复，但是index得去重才行。所以一个循环不支持index的是否去重的操作，只最终结果的是否重复操作，所以就不行
     * <p>
     * 二者也很好转换，具体使用，根据场景不同灵活变更
     * ps:一个循环和两个循环的转换方法，注释开头已经说了
     * 
     * @param str 以逗号分隔的字符串，也可以直接是list或set，因为我最终也是要转的，看具体情况变更，修改方法参数即可
     * @param len 随机的结果个数
     */
    public static List<String> random(String str, int len) {
        // 这句代码的思路：先将string转为set，目的是去重；然后再转成list，因为set是无序的，所以我需要通过有序，使用序列来操作获取集合元素。
        // ps：由于从 字符串->数组->set 只能通过 字符串->数组->借助list生成set  所以要反复转两次的list
        List<String> list = new ArrayList<>(new HashSet<>(Arrays.asList(str.split(","))));
        int llen = list.size();
        if (llen < len) len = llen;

        Set<Integer> rIndex = new HashSet<>();
        do {
            rIndex.add((int) (Math.random() * llen));
        } while (rIndex.size() < len);
        System.out.println(rIndex);

        List<String> rlist = new ArrayList<>();
        for (Integer index : rIndex) {
            rlist.add(list.get(index));
        }
        System.out.println(rlist);

        return rlist;
    }

	/**
     * 通过出生日期获取生日
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

    public static void main(String[] s) {
        // System.out.println(dollar2cents(23.6566));
        // System.out.println(Mathc.noroundDouble(23.6566));

        // int[] arr = new int[]{1, 3, 2, 8, 3};
        // sortSelect(arr);

        // System.out.println(checkPhone("13666665555"));

        // random("11,23,33,22,23,44,88,332,56", 5);

    }

}
