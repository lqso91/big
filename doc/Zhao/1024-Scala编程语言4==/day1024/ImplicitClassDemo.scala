package day1024


//使用隐式类

object ImplicitClassDemo {
  
  //定义一个隐式类，来增强1这个对象（类）的功能
  implicit class Calc(x:Int){
    //定义add方法
    def add(y:Int):Int = x + y
  }
  
  def main(args: Array[String]): Unit = {
    //执行两个数字的求和
    println("两个数字的和是：" + 1.add(2)) //实际上是调用的Calc.add方法
  }
}