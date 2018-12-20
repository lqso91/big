package day0919.revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RevertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		//���ݣ�/indexdata/data01.txt
		//�õ���Ӧ�ļ���
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		//�������ļ���
		//�õ����һ��б�ߵ�λ��
		int index = path.lastIndexOf("/");
		String fileName = path.substring(index+1);
		
		//���ݣ�I love Beijing and love Shanghai
		String data = value1.toString();
		String[] words = data.split(" ");
		
		//���
		for(String word:words){
			context.write(new Text(word+":"+fileName), new Text("1"));
		}
	}
}















