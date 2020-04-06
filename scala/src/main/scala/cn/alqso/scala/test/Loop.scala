package cn.alqso.scala.test

import scala.math.sqrt
import util.control.Breaks._

object Loop {
    def main(args: Array[String]): Unit = {
        for (i <- 1 to 100) {
            print(s"$i, ")
        }
        
        println("--------------- 求素数")
        
        for (i <- 101 to 10000) {
//            println(s"i = $i")
            
            val a = sqrt(i).toInt
            var b = false
            
            breakable {
                for (j <- 2 to a) {
                    if (i % j == 0) {
                        b = true
                        break
                    }
                }
            }
            
            if (!b) {
                println(s"$i 是素数。")
            }
            
        }
    
        println("--------------- 冒泡排序")
        val a = Array(4, 35, 22, 6, 10, 9, 10, 32, 14, 55, 88, 18)
        println("排序前：")
        a.foreach(f => print(s"$f "))
        println
        
        for (i <- a.indices){
            for (j <- 0 until a.length - 1 - i){
                if(a(j) > a(j + 1)){
                    val temp = a(j)
                    a(j) = a(j + 1)
                    a(j + 1) = temp
                }
            }
            
            print(s"第${i+1}次冒泡：")
            a.foreach(f => print(s"$f "))
            println
        }
        
        println("排序后：")
        a.foreach(f => print(s"$f "))
    }
}
