package day1019

//定义一个类
class Student3(var stuName:String)


object Student3 {
  //定义Student3的apply方法
  def apply(name:String) = {
    println("*********调用到了apply方法*********")
    new Student3(name)  //调用到了主构造器
  }
  
  //测试程序
  def main(args: Array[String]): Unit = {
    //通过主构造器创建学生对象
    var s1 = new Student3("Tom")
    println(s1.stuName)
    
    //通过apply方法创建学生对象
    var s2 = Student3("Mary")
    println(s2.stuName)
  }
}