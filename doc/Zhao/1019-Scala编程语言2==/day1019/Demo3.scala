package day1019

//�����ֶΣ����ԣ���û�г�ʼֵ���ֶ�

//����һ������
abstract class Person1{
  //��������ֶΣ�ֻ��get����
  val id:Int
  val name:String
}

//���ࣺ���������û���ṩ����ĳ����ֶεĳ�ʼֵ������Ҳ�����ǳ����
abstract class Employee1 extends Person1{
  
}

//��������ṩ��ʼֵ�����԰ѳ����ֶηŵ���������
class Employee2(val id:Int,val name:String) extends Person1{
//  val id:Int = 1
//  val name:String  = ""
}

object Demo3 {
  
}