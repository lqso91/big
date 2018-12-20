package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger
import org.apache.log4j.Level

/*
 * ����˵��ÿ10���ӣ��ѹ�ȥ30������ݽ���WordCount
 */
object MyNetworkWordCountByWindow {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)    
    
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //����������1��conf����    2������ʱ����:ÿ��3��
    val ssc = new StreamingContext(conf,Seconds(3)) 
    
    //����һ��DStream����netcat��������������
    val lines = ssc.socketTextStream("192.168.157.111", 1234, StorageLevel.MEMORY_ONLY)

    //�ִʣ�ÿ�����ʼ�һ����
    val words = lines.flatMap(_.split(" ")).map((_,1))
    
    //����˵��ÿ10���ӣ��ѹ�ȥ30������ݽ���WordCount  -------> ����
    //��ȷ��  ÿ9���ӣ��ѹ�ȥ30������ݽ���WordCount
    /*
     * ������reduceFunc��ʾҪ���еĲ�����ʲô
     * ������windowDuration�Ǵ��ڵĴ�С
     * ������slideDuration���ڻ����ľ���
     * words.reduceByWindow(reduceFunc, windowDuration, slideDuration)
     * 
     * ����������<key,value>������
     */
    val result = words.reduceByKeyAndWindow((x:Int,y:Int)=>(x + y), Seconds(30), Seconds(9))
    
    //��ӡ����
    result.print()
    
    ssc.start()
    ssc.awaitTermination()
    
  }
}














