package day0912.partition;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MyPartitionerMain {

	public static void main(String[] args) throws Exception {
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(MyPartitionerMain.class);
		
		job.setMapperClass(MyPartitionerMapper.class);
		job.setMapOutputKeyClass(IntWritable.class); //k2 �ǲ��ź�
		job.setMapOutputValueClass(Emp.class);  // v2�������Ա������
		
		//�����������
		job.setPartitionerClass(MyPartitioner.class);	
		//ָ�������ĸ���
		job.setNumReduceTasks(3);
		
		job.setReducerClass(MyPartitionerReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Emp.class);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}

}
