package day1022

//模式匹配
object Demo1 {
  def main(args: Array[String]): Unit = {
//    //1、相当于是一个switch case
//    var sign = 0
//    var ch1 = '-'
//    ch1 match {
//      case '+' => sign = 1
//      case '-' => sign = -1
//      case _ => sign = 0
//    }
//    //下划线表示其他的值    
//    println(sign)
    
//    //2、Scala的守卫：匹配某种类型的所有值
//    //匹配所有的数字
//    var ch2 = '6'
//    var digit:Int = -1
//    ch2 match {
//      case '+' => println("这是一个加号")
//      case '-' => println("这是一个减号")
//      case _ if Character.isDigit(ch2) => digit = Character.digit(ch2, 10)  //匹配所有的数字，如果ch2是一个数字，付给digit 10表示十进制
//      case _ => println("其他")
//    }
//    println(digit)
    
//      //3、 模式匹配中的变量
//    var mystr = "Hello World"
//    //取出某个字符
//    mystr(7) match {
//      case '+' => println("这是一个加号")
//      case '-' => println("这是一个减号")
//      case ch => println("这个字符是:" + ch)  //case语句中可以使用变量，ch相当于是一个常量
//    }
    
    //4、匹配类型，相当于Java中的instanceof
//    var v4:Any = 100    //Any表示任意的类型，相当于Java中的Object。最终v4其实是一个整数
//    v4 match {
//      case x:Int => println("这是一个整数")
//      case s:String => println("这是一个字符串")
//      case _ => println("其他的类型")
//    }
    
    //5、匹配数组和列表
    var myArray = Array(1,2,3)
    myArray match {
      case Array(0) => println("数组中只有一个零")
      case Array(x,y) => println("数组包含两个元素")
      case Array(x,y,z) => println("数组包含三个元素")
      case Array(x,_*)  => println("这是数组，包含多个元素")   //相当于default的操作
    }
    
    var myList = List(1,2,3)
    myList match {
      case List(0) => println("列表中只有一个零")
      case List(x,y) => println("列表包含两个元素，和是："+ (x+y))
      case List(x,y,z) => println("列表包含三个元素，和是："+ (x+y+z))
      case List(x,_*) => println("这是列表，包含多个元素")   //相当于default的操作
    }
    
    
  }
}













