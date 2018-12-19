package day1024

//定义一个隐式转换函数
//水果
class Fruit(name:String){
  //得到水果的名字
  def getFruitName():String =  name
}

//猴子：喜欢吃水果
class Monkey(f:Fruit){
  //输出
  def say() = {println("Monkey like " + f.getFruitName())}
}


object ImplicitDemo {
  
  //定义一个隐式转换函数，把Fruit转成Monkey
  implicit def fruit2Money(f:Fruit):Monkey = {new Monkey(f)}
  
  def main(args: Array[String]): Unit = {
    //定义一个水果的对象
    var f:Fruit = new Fruit("Banana")
    
    //问题是：能否调用下面的函数
    //如果可以把Fruit转成Monkey就可以调用
    f.say()
  }
}