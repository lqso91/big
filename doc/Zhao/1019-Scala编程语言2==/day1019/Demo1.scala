package day1019

//�̳�1������ Person �ˣ�  ���� Employee Ա��

//���常��
class Person(val name:String,val age:Int){
  //���庯��
  def sayHello():String = "Hello " + name + " and the age is " + age
}

//��������
//override����ʾϣ��ʹ�������е�ֵȥ���Ǹ����е�ֵ
class Employee(override val name:String,override val age:Int,var salary:Int) extends Person(name,age){
  //�������У���д����ĺ���
  override def sayHello():String = "�����е�sayHello����"
}


object Demo1 {
  def main(args: Array[String]): Unit = {
    //���Գ���
    //����һ��Person����
    var p1 = new Person("Tom",20)
    println(p1.name+"\t"+p1.age)
    println(p1.sayHello())
    
    //����һ��Employee�Ķ���
    //����ʹ���������
    var p2:Person = new Employee("Mike",25,1000)
    println(p2.sayHello())
    
    //ͨ����������ʵ�ּ̳У�û�����ֵ�����
    var p3:Person = new Person("Mary",25){
      //��������������дsayHello����
      override def sayHello():String = "���������е�sayHello����"
    }
    println(p3.sayHello())
    
    
  }
}
















