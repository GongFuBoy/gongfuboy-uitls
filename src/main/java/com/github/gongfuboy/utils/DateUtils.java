package com.github.gongfuboy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 *
 * @author GongFuBoy
 * @date 2017/12/20
 * @time 16:00
 */
public class DateUtils {

    // 日期格式一
    public static final String format1 = "yyyy-MM-dd HH:mm:ss";

    public static String formatDate(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取当前年的最后一天
     * @return
     */
    public static Date getLastDateOfYear() {
        Calendar calendar = getLastTimeOfDate();
        calendar.set(Calendar.MONTH, 11);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取当前年的第一天
     * @return
     */
    public static Date getFirstDateOfYear() {
        Calendar calendar = getFirstTimeOfDate();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月最第一天，最早时刻
     * @return
     */
    public static Date getFirstDateOfMonth() {
        Calendar calendar = getFirstTimeOfDate();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当前月最后一天，最后时刻
     * @return
     */
    public static Date getLastDateOfMonth() {
        Calendar calendar = getLastTimeOfDate();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的最开始时刻
     * @return
     */
    public static Calendar getFirstTimeOfDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }

    /**
     * 获取一天的最后时刻
     * @return
     */
    public static Calendar getLastTimeOfDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar;
    }

}
