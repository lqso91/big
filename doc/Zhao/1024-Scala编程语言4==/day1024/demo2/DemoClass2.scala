package day1024.demo2

//��䣺Scala����䣺���ͱ�����ֵ�����Ǳ������ͻ����丸�������
//��ʾ�� -

//����: ����
class Animal

//��������
class Bird extends Animal
class Sparrow extends Bird

//������ĸ��ࣺ�Զ�������
class EatSomething[-T](t:T)

object DemoClass2 {
  def main(args: Array[String]): Unit = {
    //����һ����Զ����Ķ���
    var c1:EatSomething[Bird] = new EatSomething[Bird](new Bird)
    
    //����һ����ȸ�Զ����Ķ���
    //var c2:EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    //���⣺�ܷ��c1����c2��
    //ԭ����ղ�һ��
    var c2:EatSomething[Sparrow] = c1
  }
}