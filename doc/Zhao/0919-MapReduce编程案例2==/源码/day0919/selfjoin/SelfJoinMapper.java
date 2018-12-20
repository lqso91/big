package day0919.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// ����: 7566,JONES,MANAGER,7839,1981/4/2,2975,0,20
		String data = value1.toString();
		
		//�ִʲ���
		String[] words = data.split(",");
		
		//�������
		//1����Ϊ�ϰ��                                         Ա����
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
				
		//2����ΪԱ����                                         �ϰ��Ա����
		context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		/*
		 * ע��һ�����⣺������ݴ��ڷǷ����ݣ�һ������һ�£�������ϴ��
		 * ����������⣬һ������
		 */
	}
}












