package day1024


//ʹ����ʽ��

object ImplicitClassDemo {
  
  //����һ����ʽ�࣬����ǿ1��������ࣩ�Ĺ���
  implicit class Calc(x:Int){
    //����add����
    def add(y:Int):Int = x + y
  }
  
  def main(args: Array[String]): Unit = {
    //ִ���������ֵ����
    println("�������ֵĺ��ǣ�" + 1.add(2)) //ʵ�����ǵ��õ�Calc.add����
  }
}