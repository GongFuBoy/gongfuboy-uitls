package com.github.gongfuboy.utils.scala.excel

import java.io.FileOutputStream
import java.util.Date

import com.github.gongfuboy.utils.pojo.Student
import org.junit.Test

/**
  * Created by ZhouLiMing on 2019/3/28.
  */
class ExcelFileDownloadUtilsTest {

  @Test
  def test = {
    val students = List(new Student(1, "小一", new Date()), new Student(1, "小二", new Date()),
      new Student(2, "小三", new Date()))
    ExcelFileDownloadUtils.createHSSFWorkbook(students, 1000, new FileOutputStream("D:\\student.xls"))
  }

}