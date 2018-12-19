package day1018

object Demo1 {
  def main(args: Array[String]): Unit = {
    //for循环
    //定义一个集合
    var list = List("Mary","Tom","Mike")
    
    System.out.println("************for循环的第一种写法************");
    for(s <- list) println(s)
    
    //注意:  <- 表示Scala中的提取符
    
    System.out.println("************for循环的第二种写法************");   
    //加上一个判断条件：打印名字长度大于3 的元素
    for{
      s <- list
      if(s.length() > 3)
    }println(s)
    
    System.out.println("************for循环的第三种写法************");  
    //加上一个判断条件：打印名字长度小于等于3的元素
    for(s<- list if s.length() <= 3) println(s)
    
    //还可以使用yield关键字，作用：可以产生一个新的集合
    System.out.println("************for循环的第四种写法************");      
    //把list中的每个元素变成大写，返回一个新的集合
    var newList = for{
      s <- list
      s1 = s.toUpperCase() //把名字改成大写
    }yield(s1)
    
    for(s <- newList) println(s)
    
    
    System.out.println("************使用while************"); 
    var i = 0
    while(i < list.length){
      println(list(i))
      //自增
      i += 1
    }
    
    System.out.println("************使用do....while************"); 
    var j = 0
    do{
      println(list(j))
      j += 1
    }while(j < list.length)
      
    
      //还可以使用foreach进行迭代：相当于循环
      //也可以使用map函数，
      //foreach和map的区别：foreach没有返回值、map有返回值  ----> Spark算子的时候
    System.out.println("************使用foreach************");       
      list.foreach(println)   //使用了一个高阶函数（函数式编程）
    
  }
}

























