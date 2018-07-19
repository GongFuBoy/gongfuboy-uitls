package com.github.gongfuboy.utils.excel;

import com.github.gongfuboy.utils.pojo.Student;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by ZhouLiMing on 2018/7/19.
 */
public class ExcelFileDownloadUtilsTest {


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


    @Test
    public void test() throws Exception {
        List<Student> students = getStudents();
        ExcelFileDownloadUtils.createHSSFWorkbook(students, Integer.MAX_VALUE, new FileOutputStream("D:/students.xls"));
    }
}
