package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.Partitioner
import scala.collection.mutable


object MyTomcatLogPartitioner {
  def main(args: Array[String]): Unit = {
    //ע�⣺�����Windows��ִ�У�ָ��Hadoop��Home��Ŀ¼
    System.setProperty("hadoop.home.dir", "D:\\temp\\hadoop-2.4.1\\hadoop-2.4.1")
    
    
    //����һ��SparkContext����
    val conf = new SparkConf().setAppName("MyTomcatLogCount").setMaster("local")
    val sc = new SparkContext(conf)
    
    /*
     *	��־��192.168.88.1 - - [30/Jul/2017:12:54:38 +0800] "GET /MyDemoWeb/hadoop.jsp HTTP/1.1" 200 242
     *  ������־����Ҫ����ÿ����־���ҵ����ʵ�jsp��ҳ
     * ���أ�(hadoop.jsp,��Ӧ����־)  �൱��WordCount�е�<k2 v2>
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
            
            //����   (jsp������,������־)
            (jspName,line)
        }
    )    
    
    //�õ����ظ���jsp������  ----> ������������
    val rdd2 = rdd1.map(_._1).distinct().collect()
    
    //������������
    val myPartitioner = new MyWebPartitioner(rdd2)
    
    //��rdd1���з���
    val rdd3 = rdd1.partitionBy(myPartitioner)
    
    //���
    rdd3.saveAsTextFile("d:\\temp\\rdd3")
    
    sc.stop()
    
  }
}

//�����������򣺸���jsp������
class MyWebPartitioner(jspList:Array[String]) extends Partitioner{
  //����һ���������������������
  //String: ����jsp������   Int�����Ӧ�ķ�����
  val partitionMap = new mutable.HashMap[String,Int]()
  
  var partID = 0 //������
  for(jsp <- jspList){
    //��һ��jsp������һ������
    partitionMap.put(jsp, partID)
    
    partID += 1  //�����ż�һ
  }
  
  //ʵ�ֳ��󷽷�
  //�����ж��ٸ�����
  override def  numPartitions = partitionMap.size
  
  //����jsp�����֣��õ������ķ���
  //key ����jsp������
  override def getPartition(key:Any) = {
    partitionMap.getOrElse(key.toString,0)
  }
}


























