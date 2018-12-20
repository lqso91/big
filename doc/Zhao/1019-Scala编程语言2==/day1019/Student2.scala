package day1019

/*
 * 类的构造器：两种
1、主构造器：和类的声明在一起，并且一个类只有一个主构造器
	(var stuName:String,var age:Int)

2、辅助构造器：一个类可以有多个辅助构造器，通过关键字this
 * 
 */
class Student2(var stuName:String,var age:Int){
  
  //定义一个辅助构造器：就是一个函数
  def this(age:Int){
    //可以在辅助构造器中调用主构造器
    this("no name",age)
    println("调用到了辅助构造器")
  }
  
  //定义其他的辅助构造器
}


object Student2 {
  //测试程序
  def main(args: Array[String]): Unit = {
    //创建学生对象
    //使用主构造器
    var s1 = new Student2("Tom",20)
    println(s1.stuName+"\t"+s1.age)
    
    //使用辅助构造器
    var s2 = new Student2(25)
    println(s2.stuName+"\t"+s2.age)
  }
}
















