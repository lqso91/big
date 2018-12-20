package day1019

//抽象字段（属性）：没有初始值的字段

//定义一个父类
abstract class Person1{
  //定义抽象字段，只有get方法
  val id:Int
  val name:String
}

//子类：如果子类中没有提供父类的抽象字段的初始值，子类也必须是抽象的
abstract class Employee1 extends Person1{
  
}

//如果不想提供初始值，可以把抽象字段放到构造器中
class Employee2(val id:Int,val name:String) extends Person1{
//  val id:Int = 1
//  val name:String  = ""
}

object Demo3 {
  
}