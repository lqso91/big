package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

object MyTotalNetworkWordCount {
  def main(args: Array[String]): Unit = {
    //����һ��StreamingContext�����ԣ�localģʽΪ��
    //ע�⣺��֤CPU�ĺ������ڵ���2   --->   setMaster("local[2]") �����������߳�
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //����������1��conf����    2������ʱ����:ÿ��3��
    val ssc = new StreamingContext(conf,Seconds(3))
    
    //���ü���Ŀ¼������֮ǰ��״̬��Ϣ
    ssc.checkpoint("hdfs://192.168.157.111:9000/day1107/ckpt")
    
    //����һ��DStream����netcat��������������
    val lines = ssc.socketTextStream("192.168.157.111", 1234, StorageLevel.MEMORY_ONLY)
    
    //���е��ʵļ���
    //�ִ�
    val words = lines.flatMap(_.split(" "))
    //ÿ�����ʼ�һ����
    val wordPair = words.map(w => (w,1))
       
    //����һ��ֵ����
    /*
     * ��һ����������ǰ��ֵ�Ƕ���
     * �ڶ���������֮ǰ�Ľ���Ƕ���
     */
    val addFunc = (curreValues:Seq[Int],previousValues:Option[Int])=>{
      //�����ۼ�����
      //1���ѵ�ǰֵ�����н����ۼ�
      val currentTotal = curreValues.sum
      
      //2����֮ǰ��ֵ�������ۼ� 
      //ע�⣺���֮ǰû��ֵ������0
      //����
      Some(currentTotal + previousValues.getOrElse(0))
    }
    
    
    //�����ۼ�����
    val total = wordPair.updateStateByKey(addFunc)
    
    //���
    total.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}
















