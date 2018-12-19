package day1019

//抽象类：可以包含抽象方法，只能用于继承

//父类: 抽象类---交通工具
abstract class Vehicle{
  //定义抽象方法：没有实现的方法
  def checkType():String
}

//子类：自行车、汽车
//如果子类没有实现父类的抽象方法，子类也必须是抽象的
class Car extends Vehicle{
  def checkType():String = "I am a Car"
}

class Bike extends Vehicle{
  def checkType():String = "I am a Bike"
}


object Demo2 {
  //测试程序
  def main(args: Array[String]): Unit = {
    var v1:Vehicle = new Car
    println(v1.checkType())
    
    var v2:Vehicle = new Bike
    println(v2.checkType())    
  }
}

















