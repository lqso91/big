package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

object MyTotalNetworkWordCount {
  def main(args: Array[String]): Unit = {
    //创建一个StreamingContext对象，以：local模式为例
    //注意：保证CPU的核数大于等于2   --->   setMaster("local[2]") 开启了两个线程
    val conf = new SparkConf().setAppName("MyNetworkWordCount").setMaster("local[2]") 
    //两个参数：1、conf参数    2、采样时间间隔:每隔3秒
    val ssc = new StreamingContext(conf,Seconds(3))
    
    //设置检查点目录，保存之前的状态信息
    ssc.checkpoint("hdfs://192.168.157.111:9000/day1107/ckpt")
    
    //创建一个DStream，从netcat服务器接收数据
    val lines = ssc.socketTextStream("192.168.157.111", 1234, StorageLevel.MEMORY_ONLY)
    
    //进行单词的计数
    //分词
    val words = lines.flatMap(_.split(" "))
    //每个单词记一次数
    val wordPair = words.map(w => (w,1))
       
    //定义一个值函数
    /*
     * 第一个参数：当前的值是多少
     * 第二个参数：之前的结果是多少
     */
    val addFunc = (curreValues:Seq[Int],previousValues:Option[Int])=>{
      //进行累加运算
      //1、把当前值的序列进行累加
      val currentTotal = curreValues.sum
      
      //2、在之前的值上再来累加 
      //注意：如果之前没有值，就是0
      //返回
      Some(currentTotal + previousValues.getOrElse(0))
    }
    
    
    //进行累加运算
    val total = wordPair.updateStateByKey(addFunc)
    
    //输出
    total.print()
    
    ssc.start()
    ssc.awaitTermination()
  }
}
















