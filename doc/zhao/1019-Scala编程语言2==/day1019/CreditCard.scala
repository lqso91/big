package day1019


//ʵ��һ������ģʽ���Զ����ɿ���
object CreditCard {
  //����һ�������������ÿ��Ŀ���
  private[this] var creditCardNumber:Long = 0
  
  //���庯������������
  def generateCCNumber():Long = {
    creditCardNumber += 1
    //���ؿ���
    creditCardNumber
  }
  
  //���Գ���
  def main(args: Array[String]): Unit = {
    //�����µĿ���
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
    println(CreditCard.generateCCNumber())
  }
}