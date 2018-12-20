package day1019


//实现一个单例模式：自动生成卡号
object CreditCard {
  //定义一个变量保存信用卡的卡号
  private[this] var creditCardNumber:Long = 0
  
  //定义函数来产生卡号
  def generateCCNumber():Long = {
    creditCardNumber += 1
    //返回卡号
    creditCardNumber
  }
  
  //测试程序
  def main(args: Array[String]): Unit = {
    //产生新的卡号
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
  }
}