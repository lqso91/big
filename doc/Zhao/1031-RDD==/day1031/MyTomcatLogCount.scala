package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

//本质：就是一个WordCount 单词计数
object MyTomcatLogCount {
  def main(args: Array[String]): Unit = {
    //创建一个SparkContext对象
    val conf = new SparkConf().setAppName("MyTomcatLogCount").setMaster("local")
    val sc = new SparkContext(conf)
    
    /*
     *	日志：192.168.88.1 - - [30/Jul/2017:12:54:38 +0800] "GET /MyDemoWeb/hadoop.jsp HTTP/1.1" 200 242
     *  读入日志，需要解析每行日志，找到访问的jsp网页
     * 返回：(hadoop.jsp,1)  相当于WordCount中的<k2 v2>
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
            
            //返回
            (jspName,1)
        }
    )
    
    //按照jsp的名字进行聚合操作，类似WordCount
    val rdd2 = rdd1.reduceByKey(_+_)  // ----> 得到：所有的jsp访问的总量  (hadoop.jsp,9)  (oracle.jsp,9)
    
    //排序：按照value进行排序，降序
    val rdd3 = rdd2.sortBy(_._2,false)
    
    //取出访问量最高的两个网页
    println(rdd3.take(2).toBuffer)
    
    sc.stop()
    
  }
}








