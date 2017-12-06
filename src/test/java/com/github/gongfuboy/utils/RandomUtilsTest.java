package com.github.gongfuboy.utils;

import org.junit.Test;

/**
 * RandomUtils测试类
 *
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 21:56
 */
public class RandomUtilsTest {

    @Test
    public void testCreateRondomString() {
        System.out.println(RandomUtils.createRondomString(6, 0, 10));
    }
}
