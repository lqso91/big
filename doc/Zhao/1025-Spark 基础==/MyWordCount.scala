package day1025

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

/*
 * ͨ��Spark Submit�ύ
 * bin/spark-submit --master spark://bigdata111:7077 --class day1025.MyWordCount /root/temp/demo1.jar hdfs://bigdata111:9000/input/data.txt hdfs://bigdata111:9000/output/1025/demo1
 */

object MyWordCount {
  def main(args: Array[String]): Unit = {
    //���������������Ϣ
    //�������Master=local����ʾ�����ڱ���ģʽ��
    //������м�Ⱥģʽ�ϣ�����Ҫ����Master
    //val conf = new SparkConf().setAppName("MyWordCount").setMaster("local")
    val conf = new SparkConf().setAppName("MyWordCount")
    
    //����һ��SparkContext����
    val sc = new SparkContext(conf)
    
    //ִ��WordCount
    val result = sc.textFile(args(0))
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      
    //��ӡ����Ļ��
    //result.foreach(println)
      
    //�����HDFS
    result.saveAsTextFile(args(1))
      
    //ֹͣSparkContext
    sc.stop()
  }
}