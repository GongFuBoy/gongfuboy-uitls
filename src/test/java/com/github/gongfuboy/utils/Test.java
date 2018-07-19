package com.github.gongfuboy.utils;

import com.github.gongfuboy.utils.pojo.Student;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * @author GongFuBoy
 * @date 2017/12/12
 * @time 22:41
 */
public class Test {

    protected String a;

    @org.junit.Test
    public void test() {
        List<Student> students = getStudents();
        String json = new Gson().toJson(students);
        System.out.println(json);
        Object o = new Gson().fromJson(json, new TypeToken<List<Student>>() {
        }.getType());
        List<Student> o1 = (List<Student>) o;
        System.out.println(o1.size());
        for (Student student : o1) {
            System.out.println(new Gson().toJson(student));
        }
    }

    private static List<Student> getStudents() {
        List<Student> students = Lists.newArrayList();
        Student user1 = new Student(1, null, new Date());
        Student user2 = new Student(2, "李四", new Date());
        Student user3 = new Student(3, "王五", new Date());
        students.add(user1);
        students.add(user2);
        students.add(user3);
        return students;
    }
}
