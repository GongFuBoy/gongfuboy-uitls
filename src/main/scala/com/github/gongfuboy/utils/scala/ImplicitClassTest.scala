package com.github.gongfuboy.utils.scala

/**
  * Created by ZhouLiMing on 2018/8/24.
  */
object ImplicitClassTest {

  import com.github.gongfuboy.utils.scala.Helpers.IntWithTimes

  def main(args: Array[String]): Unit = {

    5.times[String](myPrintln("temp string"))
  }

  def myPrintln(sourceString: String) = {
    println(sourceString)
    sourceString + "修改后的"
  }
}

object Helpers {
  implicit class IntWithTimes(x: Int) {
    def times[A](f: => A): Unit = {
      def loop(current: Int): Unit =
        if(current > 0) {
          println(f)
          loop(current - 1)
        }
      loop(x)
    }
  }
}