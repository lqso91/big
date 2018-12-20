package day1019

//使用App对象：应用程序对象
object HelloWorld extends App {
  
//  def main(args: Array[String]): Unit = {
//    println("Hello World")
//  }
  
  //把main函数中的程序直接写到object中
  println("Hello World")
  
  //也可以直接通过args获取命令行的参数
  if(args.length > 0){
    println("有参数")
  }else{
    println("没有有参数")
  }
}