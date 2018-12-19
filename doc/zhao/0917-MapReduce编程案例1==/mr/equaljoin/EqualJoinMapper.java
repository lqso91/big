package day0917.mr.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EqualJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݿ����ǲ��ţ�Ҳ������Ա��
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		//�ж�����ĳ���
		if(words.length == 3){
			//�õ��ǲ������ݣ����ź� ��������
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
		}else{
			//Ա������ : Ա���Ĳ��ź� Ա��������
			context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
		}
	
	}
}


















