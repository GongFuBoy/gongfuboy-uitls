package com.github.gongfuboy.utils.scala.caseclass

/**
  * Created by ZhouLiMing on 2018/9/5.
  */
case class CaseClassTest(age: Int, name: String)

object Test {

  def main(args: Array[String]): Unit = {
    val strings: List[String] = List("1", "2").map(x => x + "====")
    List.empty.dropWhile(x => x == "")
    List.empty.sortBy(x => x.hashCode())
    println(strings)
  }

}