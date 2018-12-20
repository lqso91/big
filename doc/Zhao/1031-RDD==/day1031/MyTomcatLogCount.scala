package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

//���ʣ�����һ��WordCount ���ʼ���
object MyTomcatLogCount {
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
    
    //����jsp�����ֽ��оۺϲ���������WordCount
    val rdd2 = rdd1.reduceByKey(_+_)  // ----> �õ������е�jsp���ʵ�����  (hadoop.jsp,9)  (oracle.jsp,9)
    
    //���򣺰���value�������򣬽���
    val rdd3 = rdd2.sortBy(_._2,false)
    
    //ȡ����������ߵ�������ҳ
    println(rdd3.take(2).toBuffer)
    
    sc.stop()
    
  }
}








