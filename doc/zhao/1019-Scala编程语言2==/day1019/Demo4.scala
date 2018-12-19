package day1019

/*
 * ���ʣ�trait��������Java�еĳ����࣬����֧�ֶ��ؼ̳�
	 ���ʣ����ǳ����࣬�ص㣺֧�ֶ��ؼ̳�
 */

//�����������࣬����� trait

//��һ��trait�����˵���Ϣ
trait Human{
  //�����ֶ�
  val id:Int
  val name:String
}

//�ڶ���trait����һЩ����
trait Actions{
  //����һ��������
  def getActionName():String
}

//�������࣬�ѳ����ֶη��빹������
class Student4(val id:Int,val name:String) extends Human with Actions{
  //ʵ�ֳ��󷽷�
  def getActionName():String = "Action is running"
}

object Demo4 {
   //���Գ���
  def main(args: Array[String]): Unit = {
    //����һ��ѧ������
    var s1 = new Student4(1,"Tom")
    println(s1.id+"\t"+s1.name)
    println(s1.getActionName())
  }
}












