package day1102

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

object MyTomcatLogCountToOracle {
  def main(args: Array[String]): Unit = {
    //����һ��SparkContext����
    val conf = new SparkConf().setAppName("MyTomcatLogCount").setMaster("local")
    val sc = new SparkContext(conf)
    
    /*
     *	��־��192.168.88.1 - - [30/Jul/2017:12:54:38 +0800] "GET /MyDemoWeb/hadoop.jsp HTTP/1.1" 200 242
     *  ������־����Ҫ����ÿ����־���ҵ����ʵ�jsp��ҳ
     * ���أ�(hadoop.jsp,1)  �൱��WordCount�е�<k2 v2>
     */
    val rdd1 = sc.textFile("d:\\temp\\localhost_access_log.2017-07-30.txt").map(
          line => {
            //�����ַ������ҵ�jsp������
            //1���õ�����˫���ŵ�λ��
            val index1 = line.indexOf("\"")
            val index2 = line.lastIndexOf("\"")
            val line1 = line.substring(index1+1,index2)  //------> �õ���GET /MyDemoWeb/hadoop.jsp HTTP/1.1
            
            //�õ������ո��λ��
            val index3 = line1.indexOf(" ")
            val index4 = line1.lastIndexOf(" ")
            val line2 = line1.substring(index3+1,index4) // -----> �õ���/MyDemoWeb/hadoop.jsp
            
            //�õ�jsp������
            val jspName = line2.substring(line2.lastIndexOf("/") + 1)  // -----> �õ�  hadoop.jsp
            
            //����
            (jspName,1)
        }
    )    
    
    //��rdd1�����ݱ��浽Oracle������һ�ű�  create table mydata(jsname varchar2(40),countNumber number);
    //���⣺Ӧ��ʹ��map������Ӧ��ʹ��foreach��������
    //����Ĵ����Ǵ���ģ�����
//    var conn:Connection = null
//    var pst:PreparedStatement = null
//    
//    try{
//      //����һ��Connection�����ݿ�
//      conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", 
//                                                        "scott", "tiger");
//      pst = conn.prepareStatement("insert into mydata values(?,?)")                                                     
//      
//      //��ÿ�����ݱ��浽Oracle��
//      rdd1.foreach(f =>{
//         //��ֵ
//        pst.setString(1, f._1)  //��ҳ������
//        pst.setInt(2, f._2)    //��һ����
//        //ִ��
//        pst.executeUpdate()
//      })
//    }catch{
//      case e1:Exception => e1.printStackTrace()
//    }finally{
//      if(pst != null) pst.close()
//      if(conn != null) conn.close()
//    }
    
    //��һ���޸ķ�ʽ������   ----> foreach�����ÿ�����ݶ��ᴴ��Connection��PreparedStatement
    //��ÿ�����ݱ��浽Oracle��
//    rdd1.foreach(f =>{
//      var conn:Connection = null
//      var pst:PreparedStatement = null
//      
//      try{
//        //����һ��Connection�����ݿ�
//        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", 
//                                                        "scott", "tiger");
//        pst = conn.prepareStatement("insert into mydata values(?,?)")      
//        
//         //��ֵ
//        pst.setString(1, f._1)  //��ҳ������
//        pst.setInt(2, f._2)    //��һ����
//        //ִ��
//        pst.executeUpdate()
//      }catch{
//        case e1:Exception => e1.printStackTrace()
//      }finally{
//        if(pst != null) pst.close()
//        if(conn != null) conn.close()
//      }
//    })
    
    //�ڶ����޸ķ�ʽ����Է��������в���
    rdd1.foreachPartition(saveToOracle)
    
    sc.stop()
    
  }
  
  //����һ����������Է������в������ѷ����е����ݱ��浽Oracle��
  def saveToOracle(it:Iterator[(String,Int)]) = {
    var conn:Connection  = null
    var pst:PreparedStatement = null
    
    try{
      conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", "scott","tiger")
      pst = conn.prepareStatement("insert into mydata values(?,?)")   
      
      //�ѷ����е�Ԫ�ر��浽Oracle
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




































