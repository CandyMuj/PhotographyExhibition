package com.cc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datec {
    public static final String DATEFORMAT = "yyyy-MM-dd";
    public static final String DATEFORMAT_A = "yyyyMMdd";
    public static final String DATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    public Calendar calendar = Calendar.getInstance();

    public static Datec Now() {
        return new Datec();
    }

    public static String NowString() {
        return Now().toString();
    }

    public static String NoDatecString() {
        return Now().toDateString();
    }

    public static Datec Today() {
        Datec today = Now();
        today.setZero();
        return today;
    }

    public static Datec init(String str) {
        return dinit(str, "yyyy-MM-dd");
    }

    public static Datec init(String str, String fmt) {
        return dinit(str, fmt);
    }

    private static Datec dinit(String str, String fmt) {
        SimpleDateFormat ft = new SimpleDateFormat(fmt);
        Datec w = new Datec();
        w.calendar = Calendar.getInstance();

        try {
            Date date = ft.parse(str);
            w.calendar.setTime(date);
        } catch (Exception var5) {
            var5.printStackTrace();
            w = null;
        }

        return w;
    }

    public Datec() {
    }

    public Datec(long d) {
        this.calendar.setTimeInMillis(d);
    }

    public Datec(int year, int month, int day) {
        this.calendar.set(year, month, day);
        this.setZero();
    }

    public Datec getYear() {
        this.calendar.get(1);
        return this;
    }

    public Datec setYear(int year) {
        this.calendar.set(1, year);
        return this;
    }

    public Datec addYear(int years) {
        this.calendar.add(1, years);
        return this;
    }

    public Datec addMinute(int minute) {
        this.calendar.add(12, minute);
        return this;
    }

    public int getMonth() {
        return this.calendar.get(2) + 1;
    }

    public Datec setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.calendar.set(2, month - 1);
        }

        return this;
    }

    public Datec addMonth(int months) {
        this.calendar.add(2, months);
        return this;
    }

    public int getDay() {
        return this.calendar.get(5);
    }

    public Datec setDay(int day) {
        this.calendar.set(5, day);
        return this;
    }

    public Datec addDay(int days) {
        this.calendar.add(5, days);
        return this;
    }

    public Datec setHour(int hour) {
        this.calendar.set(11, hour);
        return this;
    }

    public int getHour() {
        return this.calendar.get(11);
    }

    public Datec addHour(int hours) {
        this.calendar.add(11, hours);
        return this;
    }

    public Datec setSecond(int seconds) {
        this.calendar.set(13, seconds);
        return this;
    }

    public int getSecond() {
        return this.calendar.get(13);
    }

    public int getMinute() {
        return this.calendar.get(12);
    }

    public Datec addSecond(int seconds) {
        this.calendar.add(13, seconds);
        return this;
    }

    public int getWeek() {
        return this.calendar.get(7);
    }

    public String getWeekCn() {
        String w = "日一二三四五六七";
        int i = this.getWeek();
        return "星期" + w.substring(i - 1, i);
    }

    public long getTime() {
        return this.calendar.getTimeInMillis();
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public Datec setCalendar(Calendar calendar) {
        this.calendar = calendar;
        return this;
    }

    public void setZero() {
        this.calendar.set(11, 0);
        this.calendar.set(12, 0);
        this.calendar.set(13, 0);
        this.calendar.set(14, 0);
    }

    public String toDateString() {
        return this.toString("yyyy-MM-dd");
    }

    public String toTimeString() {
        return this.toString("HH:mm:ss");
    }

    public String toString(String format) {
        SimpleDateFormat ft = new SimpleDateFormat(format);
        return ft.format(this.calendar.getTime());
    }

    public String toString() {
        return this.toString("yyyy-MM-dd HH:mm:ss");
    }
}
