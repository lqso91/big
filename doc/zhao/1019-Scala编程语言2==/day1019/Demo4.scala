package day1019

/*
 * 特质（trait）：类似Java中的抽象类，并且支持多重继承
	 本质：就是抽象类，特点：支持多重继承
 */

//定义两个父类，定义成 trait

//第一个trait代表人的信息
trait Human{
  //抽象字段
  val id:Int
  val name:String
}

//第二个trait代表一些动作
trait Actions{
  //定义一个抽象函数
  def getActionName():String
}

//定义子类，把抽象字段放入构造器中
class Student4(val id:Int,val name:String) extends Human with Actions{
  //实现抽象方法
  def getActionName():String = "Action is running"
}

object Demo4 {
   //测试程序
  def main(args: Array[String]): Unit = {
    //创建一个学生对象
    var s1 = new Student4(1,"Tom")
    println(s1.id+"\t"+s1.name)
    println(s1.getActionName())
  }
}












