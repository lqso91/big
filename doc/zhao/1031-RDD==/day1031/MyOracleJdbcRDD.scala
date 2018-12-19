package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.sql.DriverManager
import org.apache.spark.rdd.JdbcRDD

/*
 * ���ʹ��JdbcRDD
 * ��������ѯ10�Ų��ţ����ʴ���2000��Ա��
 *  select * from emp where sal > ? and deptno =? 
 *
 */
object MyOracleJdbcRDD {
  //ע����������ȡ����
  val connection = () =>{
    Class.forName("oracle.jdbc.OracleDriver").newInstance()
    DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", "scott", "tiger")
  }
  
  def main(args: Array[String]): Unit = {
    //����һ��SparkContext����
    val conf = new SparkConf().setAppName("MyOracleJdbcRDD").setMaster("local")
    val sc = new SparkContext(conf)
    
    //����һ��JdbcRDD��ִ�в�ѯ
    val oracleRDD = new JdbcRDD(sc,
                               connection,
                               "select * from emp where sal > ? and deptno =?",
                               2000,
                               10,
                               2,
                               r=>{
                                 //����Ա��������нˮ
                                 val ename = r.getString(2)
                                 val sal = r.getInt(6)
                                 
                                 (ename,sal)
                               })
    
    val result = oracleRDD.collect()
    //���
    println(result.toBuffer)
    
    sc.stop()
  }
}














