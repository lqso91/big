package day1024.demo1

//Э�䣺Scala��Э�䣺���ͱ�����ֵ�����Ǳ������ͻ��������������
//��ʾ��+ 

//����: ����
class Animal

//��������
class Bird extends Animal
class Sparrow extends Bird

//������ĸ��ࣺ�Զ�������
class EatSomething[+T](t:T)

object DemoClass1 {
  //���Գ���
  def main(args: Array[String]): Unit = {
    //����һ����Զ����Ķ���
    var c1:EatSomething[Bird] = new EatSomething[Bird](new Bird)
    
    //����һ������Զ����Ķ���
    //var c2:EatSomething[Animal] = new EatSomething[Animal](new Animal)
    //�����ǣ��ܷ��c1����c2��
    //ԭ�򣺾���Bird��Animal�����࣬����EatSomething[Bird]����EatSomething[Animal]������
    var c2:EatSomething[Animal] = c1
    
    //�پ�һ������
    var c3:EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    var c4:EatSomething[Animal] = c3
  }
}










