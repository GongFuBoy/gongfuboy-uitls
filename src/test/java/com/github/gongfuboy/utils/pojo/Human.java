package com.github.gongfuboy.utils.pojo;

import com.github.gongfuboy.utils.annotation.IgnoreField;

/**
 * @author GongFuBoy
 * @date 2017/12/5
 * @time 14:49
 */
public class Human {

    private String name;

    @IgnoreField
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
