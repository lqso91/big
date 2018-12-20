package day1102

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

object MyTomcatLogCountToOracle {
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
    
    //把rdd1的数据保存到Oracle，创建一张表  create table mydata(jsname varchar2(40),countNumber number);
    //问题：应该使用map，还是应该使用foreach？？？？
    //下面的代码是错误的！！！
//    var conn:Connection = null
//    var pst:PreparedStatement = null
//    
//    try{
//      //创建一个Connection，数据库
//      conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", 
//                                                        "scott", "tiger");
//      pst = conn.prepareStatement("insert into mydata values(?,?)")                                                     
//      
//      //把每个数据保存到Oracle中
//      rdd1.foreach(f =>{
//         //赋值
//        pst.setString(1, f._1)  //网页的名字
//        pst.setInt(2, f._2)    //记一次数
//        //执行
//        pst.executeUpdate()
//      })
//    }catch{
//      case e1:Exception => e1.printStackTrace()
//    }finally{
//      if(pst != null) pst.close()
//      if(conn != null) conn.close()
//    }
    
    //第一种修改方式：不好   ----> foreach会针对每条数据都会创建Connection和PreparedStatement
    //把每个数据保存到Oracle中
//    rdd1.foreach(f =>{
//      var conn:Connection = null
//      var pst:PreparedStatement = null
//      
//      try{
//        //创建一个Connection，数据库
//        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", 
//                                                        "scott", "tiger");
//        pst = conn.prepareStatement("insert into mydata values(?,?)")      
//        
//         //赋值
//        pst.setString(1, f._1)  //网页的名字
//        pst.setInt(2, f._2)    //记一次数
//        //执行
//        pst.executeUpdate()
//      }catch{
//        case e1:Exception => e1.printStackTrace()
//      }finally{
//        if(pst != null) pst.close()
//        if(conn != null) conn.close()
//      }
//    })
    
    //第二种修改方式：针对分区来进行操作
    rdd1.foreachPartition(saveToOracle)
    
    sc.stop()
    
  }
  
  //定义一个函数，针对分区进行操作，把分区中的数据保存到Oracle中
  def saveToOracle(it:Iterator[(String,Int)]) = {
    var conn:Connection  = null
    var pst:PreparedStatement = null
    
    try{
      conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", "scott","tiger")
      pst = conn.prepareStatement("insert into mydata values(?,?)")   
      
      //把分区中的元素保存到Oracle
      it.foreach(data =>{
        pst.setString(1, data._1)
        pst.setInt(2, data._2)
        pst.executeUpdate()
      }) 
    }catch{
      case e1:Exception => e1.printStackTrace()
    }finally{
      if(pst != null) pst.close()
      if(conn != null) conn.close()
    }
  }
  
}




































