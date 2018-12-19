package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

/*
 * 知识点：（最核心的）
 * 1、创建一个StreamingContext  ----> 核心创建一个：DStream（离散流）
 * 2、DStream的表现形式：就是一个RDD
 * 3、使用DStream把连续的数据流变成不连续的RDD
 */
object MyNetworkWordCount {
  def main(args: Array[String]): Unit = {
    //创建一个StreamingContext对象，以：local模式为例
    //注意：保证CPU的核数大于等于2   --->   setMaster("local[2]") 开启了两个线程
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //两个参数：1、conf参数    2、采样时间间隔:每隔3秒
    val ssc = new StreamingContext(conf,Seconds(3))
    
    //创建一个DStream，从netcat服务器接收数据
    val lines = ssc.socketTextStream("192.168.157.111", 1234, StorageLevel.MEMORY_ONLY)
    
    //进行单词的计数
    //分词
    val words = lines.flatMap(_.split(" "))
    //计数
    //val wordCount = words.map((_,1)).reduceByKey(_+_)
    //使用transform完成来生成一个元组，完成跟map一样的作用
    val wordPair = words.transform(x=>x.map(x=>(x,1)))
      
    //打印结果
    wordPair.print()
    
    //启动StreamingContext，进行计算
    ssc.start();
    
    //等待任务的结束
    ssc.awaitTermination()
  }
}

















