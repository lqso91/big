package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.Partitioner
import scala.collection.mutable


object MyTomcatLogPartitioner {
  def main(args: Array[String]): Unit = {
    //注意：如果在Windows上执行，指定Hadoop的Home的目录
    System.setProperty("hadoop.home.dir", "D:\\temp\\hadoop-2.4.1\\hadoop-2.4.1")
    
    
    //创建一个SparkContext对象
    val conf = new SparkConf().setAppName("MyTomcatLogCount").setMaster("local")
    val sc = new SparkContext(conf)
    
    /*
     *	日志：192.168.88.1 - - [30/Jul/2017:12:54:38 +0800] "GET /MyDemoWeb/hadoop.jsp HTTP/1.1" 200 242
     *  读入日志，需要解析每行日志，找到访问的jsp网页
     * 返回：(hadoop.jsp,对应的日志)  相当于WordCount中的<k2 v2>
     */
    val rdd1 = sc.textFile("d:\\temp\\localhost_access_log.2017-07-30.txt").map(
          line => {
            //解析字符串，找到jsp的名字
            //1、得到两个双引号的位置
            val index1 = line.indexOf("\"")
            val index2 = line.lastIndexOf("\"")
            val line1 = line.substring(index1+1,index2)  //------> 得到：GET /MyDemoWeb/hadoop.jsp HTTP/1.1
            
            //得到两个空格的位置
            val index3 = line1.indexOf(" ")
            val index4 = line1.lastIndexOf(" ")
            val line2 = line1.substring(index3+1,index4) // -----> 得到：/MyDemoWeb/hadoop.jsp
            
            //得到jsp的名字
            val jspName = line2.substring(line2.lastIndexOf("/") + 1)  // -----> 得到  hadoop.jsp
            
            //返回   (jsp的名字,访问日志)
            (jspName,line)
        }
    )    
    
    //得到不重复的jsp的名字  ----> 创建分区规则
    val rdd2 = rdd1.map(_._1).distinct().collect()
    
    //创建分区规则
    val myPartitioner = new MyWebPartitioner(rdd2)
    
    //对rdd1进行分区
    val rdd3 = rdd1.partitionBy(myPartitioner)
    
    //输出
    rdd3.saveAsTextFile("d:\\temp\\rdd3")
    
    sc.stop()
    
  }
}

//创建分区规则：根据jsp的名字
class MyWebPartitioner(jspList:Array[String]) extends Partitioner{
  //定义一个集合来保存分区的条件
  //String: 代表jsp的名字   Int代表对应的分区号
  val partitionMap = new mutable.HashMap[String,Int]()
  
  var partID = 0 //分区号
  for(jsp <- jspList){
    //有一个jsp，建立一个分区
    partitionMap.put(jsp, partID)
    
    partID += 1  //分区号加一
  }
  
  //实现抽象方法
  //返回有多少个分区
  override def  numPartitions = partitionMap.size
  
  //根据jsp的名字，得到对饮的分区
  //key 就是jsp的名字
  override def getPartition(key:Any) = {
    partitionMap.getOrElse(key.toString,0)
  }
}


























