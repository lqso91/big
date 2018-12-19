package day1019

//继承1：父类 Person 人，  子类 Employee 员工

//定义父类
class Person(val name:String,val age:Int){
  //定义函数
  def sayHello():String = "Hello " + name + " and the age is " + age
}

//定义子类
//override：表示希望使用子类中的值去覆盖父类中的值
class Employee(override val name:String,override val age:Int,var salary:Int) extends Person(name,age){
  //在子类中，重写父类的函数
  override def sayHello():String = "子类中的sayHello方法"
}


object Demo1 {
  def main(args: Array[String]): Unit = {
    //测试程序
    //创建一个Person对象
    var p1 = new Person("Tom",20)
    println(p1.name+"\t"+p1.age)
    println(p1.sayHello())
    
    //创建一个Employee的对象
    //可以使用子类对象
    var p2:Person = new Employee("Mike",25,1000)
    println(p2.sayHello())
    
    //通过匿名子类实现继承：没有名字的子类
    var p3:Person = new Person("Mary",25){
      //在匿名子类中重写sayHello方法
      override def sayHello():String = "匿名子类中的sayHello方法"
    }
    println(p3.sayHello())
    
    
  }
}
















