package day1019

/*
 * ��Ĺ�����������
1�����������������������һ�𣬲���һ����ֻ��һ����������
	(var stuName:String,var age:Int)

2��������������һ��������ж��������������ͨ���ؼ���this
 * 
 */
class Student2(var stuName:String,var age:Int){
  
  //����һ������������������һ������
  def this(age:Int){
    //�����ڸ����������е�����������
    this("no name",age)
    println("���õ��˸���������")
  }
  
  //���������ĸ���������
}


object Student2 {
  //���Գ���
  def main(args: Array[String]): Unit = {
    //����ѧ������
    //ʹ����������
    var s1 = new Student2("Tom",20)
    println(s1.stuName+"\t"+s1.age)
    
    //ʹ�ø���������
    var s2 = new Student2(25)
    println(s2.stuName+"\t"+s2.age)
  }
}
















