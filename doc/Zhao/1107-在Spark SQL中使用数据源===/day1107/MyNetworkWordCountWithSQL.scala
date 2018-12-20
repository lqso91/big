package day1107

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

object MyNetworkWordCountWithSQL {
  def main(args: Array[String]): Unit = {
    
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)     
    
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
    
    //����Spark SQL��ʹ��SQL������WordCount
    words.foreachRDD(rdd =>{
        //����һ��SparkSession����
        val spark = SparkSession.builder().config(rdd.sparkContext.getConf).getOrCreate()
        
        //��rddת��һ��DataFrame
        import spark.implicits._
        val df1 = rdd.toDF("word")  //------> ��df1��ֻ��һ���С�word��
        
        //������ͼ
        df1.createOrReplaceTempView("words")
        
        //ִ��SQL��ͨ��SQLִ��WordCount
        spark.sql("select word,count(*) from words group by word").show
    })
    
    ssc.start()
    
    ssc.awaitTermination()
    
  }
}

















