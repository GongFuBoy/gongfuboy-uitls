package com.github.gongfuboy.utils;

/**
 * 随机数工具
 *
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 21:46
 */
public class RandomUtils {

    /**
     * 生成指定长度随机
     * @param length 随机数长度
     * @param startInclusive 随机数开始数值——包含
     * @param endExclusive 随机数结束数值——不包含
     * @return
     */
    public static String createRondomString(int length, int startInclusive, int endExclusive) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(org.apache.commons.lang3.RandomUtils.nextInt(startInclusive, endExclusive));
        }
        return stringBuffer.toString();
    }
}
