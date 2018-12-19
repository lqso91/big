package day1022

//泛型类：定义类的时候，可以带有一个泛型的参数

//定义一个类：操作整数
class GenericClassInt{
  //定义一个整数的变量
  private var content:Int = 10
  
  //定义get和set
  def set(value:Int) = {content = value}
  def get():Int = {content}
}

//定义一个类：操作字符串
class GenericClassString{
  //定义一个整数的变量
  private var content:String = ""
  
  //定义get和set
  def set(value:String) = {content = value}
  def get():String = {content}
}

//问题：能否定义一个类，既可以操作整数，也可以操作字符串  ----> 定义一个泛型类
class GenericClass[T]{
  //定义一个变量
  private var content:T = _
  
  //定义get和set
  def set(value:T) = {content = value}
  def get():T = {content}
}

object GenericClass {
 def main(args: Array[String]): Unit = {
   //定义一个Int的类型
   var v1 = new GenericClass[Int]   //-----> 相当于是class GenericClassInt
   v1.set(1000)
   v1.get()
   
   //定义一个String的类型
   var v2 = new GenericClass[String] //-----> 相当于是class GenericClassString
   v2.set("Hello")
   v2.get()
 } 
}












