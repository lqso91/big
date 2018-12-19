package day1022

//�����ࣺ�������ʱ�򣬿��Դ���һ�����͵Ĳ���

//����һ���ࣺ��������
class GenericClassInt{
  //����һ�������ı���
  private var content:Int = 10
  
  //����get��set
  def set(value:Int) = {content = value}
  def get():Int = {content}
}

//����һ���ࣺ�����ַ���
class GenericClassString{
  //����һ�������ı���
  private var content:String = ""
  
  //����get��set
  def set(value:String) = {content = value}
  def get():String = {content}
}

//���⣺�ܷ���һ���࣬�ȿ��Բ���������Ҳ���Բ����ַ���  ----> ����һ��������
class GenericClass[T]{
  //����һ������
  private var content:T = _
  
  //����get��set
  def set(value:T) = {content = value}
  def get():T = {content}
}

object GenericClass {
 def main(args: Array[String]): Unit = {
   //����һ��Int������
   var v1 = new GenericClass[Int]   //-----> �൱����class GenericClassInt
   v1.set(1000)
   v1.get()
   
   //����һ��String������
   var v2 = new GenericClass[String] //-----> �൱����class GenericClassString
   v2.set("Hello")
   v2.get()
 } 
}












