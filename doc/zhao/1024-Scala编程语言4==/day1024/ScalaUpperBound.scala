package day1024

//以上界为例

//定义父类：交通工具
class Vehicle{
  //函数：驾驶
  def drive() = {println("Driving")}
}

//定义两个子类：Car、Bike
class Car extends Vehicle{
  override def drive() = {println("Car Driving")}
}

class Bike extends Vehicle{
  override def drive() = {println("Bike Driving")}
}

object ScalaUpperBound {
  //定义驾驶交通工具的函数，规定他的上界是Vehicle
  def takeVehicle[T <: Vehicle](v:T) = {v.drive()}
  
  def main(args: Array[String]): Unit = {
    //定义一个交通工具
    var v:Vehicle = new Vehicle
    takeVehicle(v)
    
    var c:Car = new Car
    takeVehicle(c)
    
    //不能传递别的类型
    //takeVehicle("Hello")
  }
}


















