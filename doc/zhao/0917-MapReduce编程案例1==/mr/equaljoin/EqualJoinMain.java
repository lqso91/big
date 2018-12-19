package day0917.mr.equaljoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class EqualJoinMain {

	public static void main(String[] args) throws Exception {
		//1������һ������
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(EqualJoinMain.class); //��������		
		
		//2��ָ�������map��map�������������
		job.setMapperClass(EqualJoinMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);  //k2����������
		job.setMapOutputValueClass(Text.class);  //v2������
	
		//3��ָ�������reduce��reduce��������ݵ�����
		job.setReducerClass(EqualJoinReducer.class);
		job.setOutputKeyClass(Text.class); //k4������
		job.setOutputValueClass(Text.class); //v4������
		
		//4��ָ�����������·������������·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//5��ִ������
		job.waitForCompletion(true);

	}

}
