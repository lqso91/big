package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger
import org.apache.log4j.Level
import scala.collection.mutable.Queue
import org.apache.spark.rdd.RDD

object RDDQueueStream {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)    
    
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //����������1��conf����    2������ʱ����:ÿ��3��
    val ssc = new StreamingContext(conf,Seconds(1))     
    
    //��Ҫ��һ�����У��������з����� ------>  ���ʣ����Ǵ�����һ������Դ
    val rddQueue = new Queue[RDD[Int]]()
    for(i<- 1 to 3){
      rddQueue += ssc.sparkContext.makeRDD(1 to 10)
      //˯һ����
      Thread.sleep(1000)
    }
    
    //�Ӷ����н������ݣ�����DStream
    val inputDStream = ssc.queueStream(rddQueue)
    
    //��������
    val result = inputDStream.map(x => (x,x*2))
    result.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}

















