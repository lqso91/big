package day1022

//ģʽƥ��
object Demo1 {
  def main(args: Array[String]): Unit = {
//    //1���൱����һ��switch case
//    var sign = 0
//    var ch1 = '-'
//    ch1 match {
//      case '+' => sign = 1
//      case '-' => sign = -1
//      case _ => sign = 0
//    }
//    //�»��߱�ʾ������ֵ    
//    println(sign)
    
//    //2��Scala��������ƥ��ĳ�����͵�����ֵ
//    //ƥ�����е�����
//    var ch2 = '6'
//    var digit:Int = -1
//    ch2 match {
//      case '+' => println("����һ���Ӻ�")
//      case '-' => println("����һ������")
//      case _ if Character.isDigit(ch2) => digit = Character.digit(ch2, 10)  //ƥ�����е����֣����ch2��һ�����֣�����digit 10��ʾʮ����
//      case _ => println("����")
//    }
//    println(digit)
    
//      //3�� ģʽƥ���еı���
//    var mystr = "Hello World"
//    //ȡ��ĳ���ַ�
//    mystr(7) match {
//      case '+' => println("����һ���Ӻ�")
//      case '-' => println("����һ������")
//      case ch => println("����ַ���:" + ch)  //case����п���ʹ�ñ�����ch�൱����һ������
//    }
    
    //4��ƥ�����ͣ��൱��Java�е�instanceof
//    var v4:Any = 100    //Any��ʾ��������ͣ��൱��Java�е�Object������v4��ʵ��һ������
//    v4 match {
//      case x:Int => println("����һ������")
//      case s:String => println("����һ���ַ���")
//      case _ => println("����������")
//    }
    
    //5��ƥ��������б�
    var myArray = Array(1,2,3)
    myArray match {
      case Array(0) => println("������ֻ��һ����")
      case Array(x,y) => println("�����������Ԫ��")
      case Array(x,y,z) => println("�����������Ԫ��")
      case Array(x,_*)  => println("�������飬�������Ԫ��")   //�൱��default�Ĳ���
    }
    
    var myList = List(1,2,3)
    myList match {
      case List(0) => println("�б���ֻ��һ����")
      case List(x,y) => println("�б��������Ԫ�أ����ǣ�"+ (x+y))
      case List(x,y,z) => println("�б��������Ԫ�أ����ǣ�"+ (x+y+z))
      case List(x,_*) => println("�����б��������Ԫ��")   //�൱��default�Ĳ���
    }
    
    
  }
}













