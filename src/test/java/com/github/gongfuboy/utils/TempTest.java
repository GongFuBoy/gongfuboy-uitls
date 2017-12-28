package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.wechat.jsticket.TokenUtils;

/**
 * @author GongFuBoy
 * @date 2017/12/12
 * @time 22:42
 */
public class TempTest {

    @org.junit.Test
    public void testString() {
        TokenUtils tokenUtils = new TokenUtils("appid1", "key1");
        TokenUtils tokenUtils1 = new TokenUtils("appid2", "key2");
        System.out.println(tokenUtils.getTOKEN_CACHE().put("temp", "temp"));
        System.out.println(tokenUtils1.getTOKEN_CACHE());
    }
}
