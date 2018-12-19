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
    //两个参数：1、conf参数    2、采样时间间隔:每隔3秒
    val ssc = new StreamingContext(conf,Seconds(1))     
    
    //需要有一个队列，往队列中放数据 ------>  本质：就是创建了一个数据源
    val rddQueue = new Queue[RDD[Int]]()
    for(i<- 1 to 3){
      rddQueue += ssc.sparkContext.makeRDD(1 to 10)
      //睡一秒钟
      Thread.sleep(1000)
    }
    
    //从队列中接收数据，创建DStream
    val inputDStream = ssc.queueStream(rddQueue)
    
    //处理数据
    val result = inputDStream.map(x => (x,x*2))
    result.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}

















