package day1019

//ʹ��App����Ӧ�ó������
object HelloWorld extends App {
  
//  def main(args: Array[String]): Unit = {
//    println("Hello World")
//  }
  
  //��main�����еĳ���ֱ��д��object��
  println("Hello World")
  
  //Ҳ����ֱ��ͨ��args��ȡ�����еĲ���
  if(args.length > 0){
    println("�в���")
  }else{
    println("û���в���")
  }
}