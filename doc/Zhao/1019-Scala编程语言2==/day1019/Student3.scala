package day1019

//����һ����
class Student3(var stuName:String)


object Student3 {
  //����Student3��apply����
  def apply(name:String) = {
    println("*********���õ���apply����*********")
    new Student3(name)  //���õ�����������
  }
  
  //���Գ���
  def main(args: Array[String]): Unit = {
    //ͨ��������������ѧ������
    var s1 = new Student3("Tom")
    println(s1.stuName)
    
    //ͨ��apply��������ѧ������
    var s2 = Student3("Mary")
    println(s2.stuName)
  }
}