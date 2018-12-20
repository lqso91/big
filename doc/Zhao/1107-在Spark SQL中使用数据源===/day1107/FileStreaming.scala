package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger
import org.apache.log4j.Level

object FileStreaming {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)    
    
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //����������1��conf����    2������ʱ����:ÿ��3��
    val ssc = new StreamingContext(conf,Seconds(3)) 
    
    //ֱ�Ӽ�ر��ص�ĳ��Ŀ¼��������µ��ļ��������Ͷ�ȡ����-----> ֱ�Ӵ�ӡ
    val lines = ssc.textFileStream("D:\\temp\\spark")
    //ֱ�Ӵ�ӡ
    lines.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}