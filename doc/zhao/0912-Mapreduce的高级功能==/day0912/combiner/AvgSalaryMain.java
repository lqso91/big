package day0912.combiner;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AvgSalaryMain {

	public static void main(String[] args) throws Exception {
		//1����������ָ���������� 
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(AvgSalaryMain.class);
		
		//2��ָ�������map��map�������������
		job.setMapperClass(AvgSalaryMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//����Combiner
		job.setCombinerClass(AvgSalaryReducer.class);
		
		//3��ָ�������reducer��reducer���������
		job.setReducerClass(AvgSalaryReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		//4��ָ����������·�������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5��ִ������
		job.waitForCompletion(true);

	}

}
