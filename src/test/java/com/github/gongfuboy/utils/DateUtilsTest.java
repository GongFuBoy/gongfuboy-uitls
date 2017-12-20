package com.github.gongfuboy.utils;

import java.text.SimpleDateFormat;

/**
 * @author GongFuBoy
 * @date 2017/12/20
 * @time 16:41
 */
public class DateUtilsTest {

    @org.junit.Test
    public void test() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(DateUtils.getFirstDateOfYear()));
        System.out.println(format.format(DateUtils.getLastDateOfYear()));
        System.out.println(format.format(DateUtils.getFirstDateOfMonth()));
        System.out.println(format.format(DateUtils.getLastDateOfMonth()));
    }
}
