package day1025

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/*
 * 通过Spark Submit提交
 * bin/spark-submit --master spark://bigdata111:7077 --class day1025.MyWordCount /root/temp/demo1.jar hdfs://bigdata111:9000/input/data.txt hdfs://bigdata111:9000/output/1025/demo1
 */

object MyWordCount {
  def main(args: Array[String]): Unit = {
    //创建任务的配置信息
    //如果设置Master=local，表示运行在本地模式上
    //如果运行集群模式上，不需要设置Master
    //val conf = new SparkConf().setAppName("MyWordCount").setMaster("local")
    val conf = new SparkConf().setAppName("MyWordCount")
    
    //创建一个SparkContext对象
    val sc = new SparkContext(conf)
    
    //执行WordCount
    val result = sc.textFile(args(0))
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      
    //打印在屏幕上
    //result.foreach(println)
      
    //输出到HDFS
    result.saveAsTextFile(args(1))
      
    //停止SparkContext
    sc.stop()
  }
}