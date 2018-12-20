package day1024

//���Ͻ�Ϊ��

//���常�ࣺ��ͨ����
class Vehicle{
  //��������ʻ
  def drive() = {println("Driving")}
}

//�����������ࣺCar��Bike
class Car extends Vehicle{
  override def drive() = {println("Car Driving")}
}

class Bike extends Vehicle{
  override def drive() = {println("Bike Driving")}
}

object ScalaUpperBound {
  //�����ʻ��ͨ���ߵĺ������涨�����Ͻ���Vehicle
  def takeVehicle[T <: Vehicle](v:T) = {v.drive()}
  
  def main(args: Array[String]): Unit = {
    //����һ����ͨ����
    var v:Vehicle = new Vehicle
    takeVehicle(v)
    
    var c:Car = new Car
    takeVehicle(c)
    
    //���ܴ��ݱ������
    //takeVehicle("Hello")
  }
}


















