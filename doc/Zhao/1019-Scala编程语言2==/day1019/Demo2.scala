package day1019

//�����ࣺ���԰������󷽷���ֻ�����ڼ̳�

//����: ������---��ͨ����
abstract class Vehicle{
  //������󷽷���û��ʵ�ֵķ���
  def checkType():String
}

//���ࣺ���г�������
//�������û��ʵ�ָ���ĳ��󷽷�������Ҳ�����ǳ����
class Car extends Vehicle{
  def checkType():String = "I am a Car"
}

class Bike extends Vehicle{
  def checkType():String = "I am a Bike"
}


object Demo2 {
  //���Գ���
  def main(args: Array[String]): Unit = {
    var v1:Vehicle = new Car
    println(v1.checkType())
    
    var v2:Vehicle = new Bike
    println(v2.checkType())    
  }
}

















