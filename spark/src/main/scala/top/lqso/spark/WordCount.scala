package top.lqso.spark
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("wordCount")
      .setMaster("local")

    val sc = new SparkContext(conf)
    sc.textFile("H:\\tmp\\words.txt")
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .sortBy(_._2, false)
      .collect()
      .foreach(println)

    sc.stop();
  }
}