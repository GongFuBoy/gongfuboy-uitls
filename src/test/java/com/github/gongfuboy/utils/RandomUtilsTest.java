package com.github.gongfuboy.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;

/**
 * RandomUtils测试类
 *
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 21:56
 */
public class RandomUtilsTest {

    @Test
    public void testCreateRondomString() throws ParseException {
        //1512612625000
        //15126126250
        String s = String.valueOf(DateUtils.parseDate("2017-12-07 10:10:25", "yyyy-MM-dd hh:mm:ss").getTime());
        System.out.println(s.substring(0, 10));
    }
}
