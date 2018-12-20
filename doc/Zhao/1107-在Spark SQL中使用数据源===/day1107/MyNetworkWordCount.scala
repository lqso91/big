package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

/*
 * ֪ʶ�㣺������ĵģ�
 * 1������һ��StreamingContext  ----> ���Ĵ���һ����DStream����ɢ����
 * 2��DStream�ı�����ʽ������һ��RDD
 * 3��ʹ��DStream����������������ɲ�������RDD
 */
object MyNetworkWordCount {
  def main(args: Array[String]): Unit = {
    //����һ��StreamingContext�����ԣ�localģʽΪ��
    //ע�⣺��֤CPU�ĺ������ڵ���2   --->   setMaster("local[2]") �����������߳�
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //����������1��conf����    2������ʱ����:ÿ��3��
    val ssc = new StreamingContext(conf,Seconds(3))
    
    //����һ��DStream����netcat��������������
    val lines = ssc.socketTextStream("192.168.157.111", 1234, StorageLevel.MEMORY_ONLY)
    
    //���е��ʵļ���
    //�ִ�
    val words = lines.flatMap(_.split(" "))
    //����
    //val wordCount = words.map((_,1)).reduceByKey(_+_)
    //ʹ��transform���������һ��Ԫ�飬��ɸ�mapһ��������
    val wordPair = words.transform(x=>x.map(x=>(x,1)))
      
    //��ӡ���
    wordPair.print()
    
    //����StreamingContext�����м���
    ssc.start();
    
    //�ȴ�����Ľ���
    ssc.awaitTermination()
  }
}

















