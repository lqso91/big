package day1022

//交通工具
class Vehicle

//子类（样本类，支持模式匹配）
case class Car(name:String) extends Vehicle
case class Bike(name:String) extends Vehicle


//使用case class进行模式匹配
object Demo2 {
  def main(args: Array[String]): Unit = {
    var aCar:Vehicle = new Car("Tom的汽车")
    
    aCar match {
      case Car(name) => println("汽车，" + name)
      case Bike(name) => println("自行车")
      case _ => println("其他的交通工具")
    }
  }
}