package day1022

//��ͨ����
class Vehicle

//���ࣨ�����֧࣬��ģʽƥ�䣩
case class Car(name:String) extends Vehicle
case class Bike(name:String) extends Vehicle


//ʹ��case class����ģʽƥ��
object Demo2 {
  def main(args: Array[String]): Unit = {
    var aCar:Vehicle = new Car("Tom������")
    
    aCar match {
      case Car(name) => println("������" + name)
      case Bike(name) => println("���г�")
      case _ => println("�����Ľ�ͨ����")
    }
  }
}