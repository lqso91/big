package day1019

import scala.collection.mutable.ArrayBuffer

//需求：定义一个学生类，同时要保存学生的课程成绩信息
class Student1{
  //定义学生的属性
  private var stuName:String = "Tom"
  private var stuAge:Int = 20
  
  //定义一个可变数组来保存学生的课程成绩
  private var courseList = new ArrayBuffer[Course]()
  
  //定义一个函数用于添加学生的课程成绩
  def addNewCourse(cname:String,grade:Int){
    //创建课程的成绩信息
    var c = new Course(cname,grade)
    
    //添加到学生的对象中
    courseList += c
  }
  
  
  //定义一个课程类：主构造器就是写在类的后面
  class Course(var courseName:String,var grade:Int){
    //定义其他的函数
  }
}


object Student1 {
  def main(args: Array[String]): Unit = {
    //测试程序
    //创建学生对象
    var s = new Student1
    
    //给学生添加课程的成绩信息
    s.addNewCourse("Chinese", 80)
    s.addNewCourse("Math", 80)
    s.addNewCourse("English", 80)
    
    //输出
    println(s.stuName + "\t" + s.stuAge)
    println("********课程成绩信息*********")
    for(c <- s.courseList) 
         println(c.courseName+"\t"+c.grade)
  }
}



















