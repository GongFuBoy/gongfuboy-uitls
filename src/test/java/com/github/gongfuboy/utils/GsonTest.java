package com.github.gongfuboy.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * @author GongFuBoy
 * @date 2017/12/14
 * @time 18:15
 */
public class GsonTest {

    @org.junit.Test
    public void testGson() {
        String s = "{\"response\":{\"message\":\"处理成功\",\"result\":\"0\",\"data\":{\"balance\":0.00,\"address\":\"地址\",\"userName\":null,\"menberId\":\"11\"}}}";
        Gson gson = new Gson();
        System.out.println(gson.fromJson(s, Map.class));
    }
}
