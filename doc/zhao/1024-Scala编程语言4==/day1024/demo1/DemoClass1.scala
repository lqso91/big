package day1024.demo1

//协变：Scala的协变：泛型变量的值可以是本身类型或者其子类的类型
//表示：+ 

//父类: 动物
class Animal

//两个子类
class Bird extends Animal
class Sparrow extends Bird

//定义第四个类：吃东西的类
class EatSomething[+T](t:T)

object DemoClass1 {
  //测试程序
  def main(args: Array[String]): Unit = {
    //定义一个鸟吃东西的对象
    var c1:EatSomething[Bird] = new EatSomething[Bird](new Bird)
    
    //创建一个动物吃东西的对象
    //var c2:EatSomething[Animal] = new EatSomething[Animal](new Animal)
    //问题是：能否把c1付给c2？
    //原因：尽管Bird是Animal的子类，但是EatSomething[Bird]不是EatSomething[Animal]的子类
    var c2:EatSomething[Animal] = c1
    
    //再举一个例子
    var c3:EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    var c4:EatSomething[Animal] = c3
  }
}










