package day1024.demo2

//逆变：Scala的逆变：泛型变量的值可以是本身类型或者其父类的类型
//表示： -

//父类: 动物
class Animal

//两个子类
class Bird extends Animal
class Sparrow extends Bird

//定义第四个类：吃东西的类
class EatSomething[-T](t:T)

object DemoClass2 {
  def main(args: Array[String]): Unit = {
    //定义一个鸟吃东西的对象
    var c1:EatSomething[Bird] = new EatSomething[Bird](new Bird)
    
    //定义一个麻雀吃东西的对象
    //var c2:EatSomething[Sparrow] = new EatSomething[Sparrow](new Sparrow)
    //问题：能否把c1付给c2？
    //原因跟刚才一样
    var c2:EatSomething[Sparrow] = c1
  }
}