package com.github.gongfuboy.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhouLiMing on 2018/5/3.
 */
public class TestClass {

    private String testName1 = "temp";

    private String testName2;

    private Object temp;

    private List<String> temp2;


    @Override
    public String toString() {
        return "TestClass{" +
                "testName1='" + testName1 + '\'' +
                ", testName2='" + testName2 + '\'' +
                ", temp=" + temp +
                ", temp2=" + temp2 +
                '}';
    }


    public static void main(String[] args) {
        List<Map<String, String>> before = new ArrayList<Map<String, String>>();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");
        before.add(hashMap);
        System.out.println(new Gson().toJson(before));

        System.out.println(new Gson().toJson(hashMap));
    }
}
