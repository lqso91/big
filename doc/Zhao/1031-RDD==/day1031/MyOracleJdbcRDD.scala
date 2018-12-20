package day1031

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.sql.DriverManager
import org.apache.spark.rdd.JdbcRDD

/*
 * 如何使用JdbcRDD
 * 举例：查询10号部门，工资大于2000的员工
 *  select * from emp where sal > ? and deptno =? 
 *
 */
object MyOracleJdbcRDD {
  //注册驱动，获取链接
  val connection = () =>{
    Class.forName("oracle.jdbc.OracleDriver").newInstance()
    DriverManager.getConnection("jdbc:oracle:thin:@192.168.157.102:1521/orcl.example.com", "scott", "tiger")
  }
  
  def main(args: Array[String]): Unit = {
    //创建一个SparkContext对象
    val conf = new SparkConf().setAppName("MyOracleJdbcRDD").setMaster("local")
    val sc = new SparkContext(conf)
    
    //创建一个JdbcRDD，执行查询
    val oracleRDD = new JdbcRDD(sc,
                               connection,
                               "select * from emp where sal > ? and deptno =?",
                               2000,
                               10,
                               2,
                               r=>{
                                 //返回员工姓名和薪水
                                 val ename = r.getString(2)
                                 val sal = r.getInt(6)
                                 
                                 (ename,sal)
                               })
    
    val result = oracleRDD.collect()
    //输出
    println(result.toBuffer)
    
    sc.stop()
  }
}














