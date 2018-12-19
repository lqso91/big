package demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

/*
 * ʹ��spark submit�ύ
 * bin/spark-submit --master spark://bigdata111:7077 --class demo.JavaWordCount /root/temp/demo2.jar hdfs://bigdata111:9000/input/data.txt
 */

public class JavaWordCount {

	public static void main(String[] args) {
		//�����ڱ���ģʽ���������öϵ�
		SparkConf conf = new SparkConf().setAppName("JavaWordCount").setMaster("local");
		
		//�����ڼ�Ⱥģʽ
		//SparkConf conf = new SparkConf().setAppName("JavaWordCount");
		
		//����һ��SparkContext���� JavaSparkContext����
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		//����HDFS������
		JavaRDD<String> rdd1 = sc.textFile(args[0]);
		
		/*
		 * �ִ�
		 * FlatMapFunction���ӿڣ����ڴ���ִʵĲ���
		 * ����:String �����ÿһ�仰
		 *     U:      ����ֵ ---> String ����
		 */
		JavaRDD<String> rdd2 = rdd1.flatMap(new FlatMapFunction<String, String>() {

			@Override
			public Iterator<String> call(String input) throws Exception {
				//����: I love Beijing
				//�ִ�
				return Arrays.asList(input.split(" ")).iterator();
			}
		});
		
		/*
		 * ÿ�����ʼ�һ����  (k2  v2)
		 * Beijing ---> (Beijing,1)
		 * ������
		 * String������
		 * k2 v2������
		 */
		JavaPairRDD<String, Integer> rdd3 = rdd2.mapToPair(new PairFunction<String, String, Integer>() {

			@Override
			public Tuple2<String, Integer> call(String word) throws Exception {
				return new Tuple2<String, Integer>(word, 1);
			}
			
		});
		
		//ִ��Reduce�Ĳ���
		JavaPairRDD<String, Integer> rdd4 = rdd3.reduceByKey(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer a, Integer b) throws Exception {
				//�ۼ�
				return a+b;
			}
		});
		
		//ִ�м���(Action)���ѽ����ӡ����Ļ��
		List<Tuple2<String,Integer>> result = rdd4.collect();
		
		for(Tuple2<String,Integer> tuple:result){
			System.out.println(tuple._1+"\t"+tuple._2);
		}
		
		//ֹͣJavaSparkContext����
		sc.stop();
	}
}











