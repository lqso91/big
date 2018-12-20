package day1019

import scala.collection.mutable.ArrayBuffer

//���󣺶���һ��ѧ���࣬ͬʱҪ����ѧ���Ŀγ̳ɼ���Ϣ
class Student1{
  //����ѧ��������
  private var stuName:String = "Tom"
  private var stuAge:Int = 20
  
  //����һ���ɱ�����������ѧ���Ŀγ̳ɼ�
  private var courseList = new ArrayBuffer[Course]()
  
  //����һ�������������ѧ���Ŀγ̳ɼ�
  def addNewCourse(cname:String,grade:Int){
    //�����γ̵ĳɼ���Ϣ
    var c = new Course(cname,grade)
    
    //��ӵ�ѧ���Ķ�����
    courseList += c
  }
  
  
  //����һ���γ��ࣺ������������д����ĺ���
  class Course(var courseName:String,var grade:Int){
    //���������ĺ���
  }
}


object Student1 {
  def main(args: Array[String]): Unit = {
    //���Գ���
    //����ѧ������
    var s = new Student1
    
    //��ѧ����ӿγ̵ĳɼ���Ϣ
    s.addNewCourse("Chinese", 80)
    s.addNewCourse("Math", 80)
    s.addNewCourse("English", 80)
    
    //���
    println(s.stuName + "\t" + s.stuAge)
    println("********�γ̳ɼ���Ϣ*********")
    for(c <- s.courseList) 
         println(c.courseName+"\t"+c.grade)
  }
}



















