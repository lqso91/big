package day1024

//����һ����ʽת������
//ˮ��
class Fruit(name:String){
  //�õ�ˮ��������
  def getFruitName():String =  name
}

//���ӣ�ϲ����ˮ��
class Monkey(f:Fruit){
  //���
  def say() = {println("Monkey like " + f.getFruitName())}
}


object ImplicitDemo {
  
  //����һ����ʽת����������Fruitת��Monkey
  implicit def fruit2Money(f:Fruit):Monkey = {new Monkey(f)}
  
  def main(args: Array[String]): Unit = {
    //����һ��ˮ���Ķ���
    var f:Fruit = new Fruit("Banana")
    
    //�����ǣ��ܷ��������ĺ���
    //������԰�Fruitת��Monkey�Ϳ��Ե���
    f.say()
  }
}