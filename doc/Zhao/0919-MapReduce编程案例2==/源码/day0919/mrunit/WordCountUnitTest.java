package day0919.mrunit;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

public class WordCountUnitTest {

	@Test
	public void testMapper() throws Exception{
		/*
		 * java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries
		 * ���û��������Ϳ��ԣ��õ�Hadoop��windows�ϵİ�װ��
		 */
		System.setProperty("hadoop.home.dir", "D:\\temp\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//����һ��Map�Ķ��󣺲��Զ���
		WordCountMapper mapper = new WordCountMapper();
		
		//����һ��MapDriver���е�Ԫ����
		MapDriver<LongWritable, Text, Text, IntWritable> driver = new MapDriver<>(mapper);
		
		//ָ��map�����룺k1  v1
		driver.withInput(new LongWritable(1), new Text("I love Beijing"));
		
		//ָ��map������� k2  v2  ------> ���������õ����
		driver.withOutput(new Text("I"), new IntWritable(1))
		      .withOutput(new Text("love"), new IntWritable(1))
		      .withOutput(new Text("Beijing"), new IntWritable(1));
		
		//ִ�е�Ԫ���ԣ��Ա�  �����Ľ��  ��  ʵ�ʵĽ��
		driver.runTest();
	}
	
	@Test
	public void testReducer() throws Exception{
		System.setProperty("hadoop.home.dir", "D:\\temp\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//����һ�����Զ���
		WordCountReducer reducer = new WordCountReducer();
		
		// ����һ��Driver
		ReduceDriver<Text, IntWritable, Text, IntWritable> driver = new ReduceDriver<>(reducer);
		
		//ָ��Driver�����룺k3  v3
		//����һ��v3 ��һ������
		List<IntWritable> value3 = new ArrayList<>();
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		
		driver.withInput(new Text("Beijing"), value3);
		
		//ָ�����������  ָ��  k4  v4
		driver.withOutput(new Text("Beijing"), new IntWritable(3));
		
		//ִ�е�Ԫ����
		driver.runTest();
	}
	
	@Test
	public void testJob() throws Exception{
		System.setProperty("hadoop.home.dir", "D:\\temp\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//�������ԵĶ���
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();
		
		//����һ��Driver
		//MapReduceDriver<K1, V1, K2, V2, K4, V4>
		MapReduceDriver<LongWritable, Text, Text, IntWritable,Text, IntWritable>
			driver = new MapReduceDriver<>(mapper,reducer);
		
		//ָ��Map������
		driver.withInput(new LongWritable(1), new Text("I love Beijing"))
			  .withInput(new LongWritable(4), new Text("I love China"))
			  .withInput(new LongWritable(7), new Text("Beijing is the capital of China"));
		
		//ָ��Reducer�����
		driver.withOutput(new Text("Beijing"), new IntWritable(2))
		      .withOutput(new Text("China"), new IntWritable(2))
		      .withOutput(new Text("I"), new IntWritable(2))
		      .withOutput(new Text("capital"), new IntWritable(1))
		      .withOutput(new Text("is"), new IntWritable(1))
		      .withOutput(new Text("love"), new IntWritable(2))
		      .withOutput(new Text("of"), new IntWritable(1))
		      .withOutput(new Text("the"), new IntWritable(1));
		
		driver.runTest();
	}
}




























