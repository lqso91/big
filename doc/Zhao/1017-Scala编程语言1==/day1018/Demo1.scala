package day1018

object Demo1 {
  def main(args: Array[String]): Unit = {
    //forѭ��
    //����һ������
    var list = List("Mary","Tom","Mike")
    
    System.out.println("************forѭ���ĵ�һ��д��************");
    for(s <- list) println(s)
    
    //ע��:  <- ��ʾScala�е���ȡ��
    
    System.out.println("************forѭ���ĵڶ���д��************");   
    //����һ���ж���������ӡ���ֳ��ȴ���3 ��Ԫ��
    for{
      s <- list
      if(s.length() > 3)
    }println(s)
    
    System.out.println("************forѭ���ĵ�����д��************");  
    //����һ���ж���������ӡ���ֳ���С�ڵ���3��Ԫ��
    for(s<- list if s.length() <= 3) println(s)
    
    //������ʹ��yield�ؼ��֣����ã����Բ���һ���µļ���
    System.out.println("************forѭ���ĵ�����д��************");      
    //��list�е�ÿ��Ԫ�ر�ɴ�д������һ���µļ���
    var newList = for{
      s <- list
      s1 = s.toUpperCase() //�����ָĳɴ�д
    }yield(s1)
    
    for(s <- newList) println(s)
    
    
    System.out.println("************ʹ��while************"); 
    var i = 0
    while(i < list.length){
      println(list(i))
      //����
      i += 1
    }
    
    System.out.println("************ʹ��do....while************"); 
    var j = 0
    do{
      println(list(j))
      j += 1
    }while(j < list.length)
      
    
      //������ʹ��foreach���е������൱��ѭ��
      //Ҳ����ʹ��map������
      //foreach��map������foreachû�з���ֵ��map�з���ֵ  ----> Spark���ӵ�ʱ��
    System.out.println("************ʹ��foreach************");       
      list.foreach(println)   //ʹ����һ���߽׺���������ʽ��̣�
    
  }
}

























