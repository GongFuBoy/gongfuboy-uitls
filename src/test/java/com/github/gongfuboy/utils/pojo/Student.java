package com.github.gongfuboy.utils.pojo;

import com.github.gongfuboy.utils.excel.Description;

import java.util.Date;

/**
 * Created by ZhouLiMing on 2018/7/19.
 */
public class Student {

    @Description("年龄")
    private int age;

    @Description("姓名")
    private String name;

    @Description("生日")
    private Date birthday;

    public Student(int age, String name, Date birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }
}
