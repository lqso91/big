package day1018

//代表一个学生的信息
class Student1 {
  //定义学生的属性
  private var stuID:Int = 0
  private var stuName:String = "Tom"
  private var age:Int = 20
  
  //定义成员方法（函数）:get 和set
  //定义名字和年龄的get和set  
  def getStuName():String = stuName
  def setStuName(newName:String) = this.stuName = newName
  
  def getStuAge():Int = age
  def setStuAge(newAge:Int) = this.age = newAge
}

//测试Student1类，创建main函数(写到Object中)
//注意：object和class的名字可以不一样，如果一样了，这个object就叫做该class的伴生对象
object Student1{
  def main(args: Array[String]): Unit = {
    //创建学生对象
    var s1 = new Student1
    
    //第一次访问属性并输出
    println(s1.getStuName()+"\t"+s1.getStuAge())
    
    //访问set方法
    s1.setStuName("Mary")
    s1.setStuAge(22)
    println(s1.getStuName()+"\t"+s1.getStuAge())
    
    //再输出一次：直接访问私有的属性
    println("*************直接访问私有的属性************")
    println(s1.stuID +"\t"+ s1.stuName+"\t"+s1.age)
    
    //注意：可以直接访问类的私有成员  为什么可以？ ----> 属性的get和set方法
    
  }
}














