package day0919.revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RevertedIndexCombiner extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text k21, Iterable<Text> v21, Context context)
			throws IOException, InterruptedException {
		// ��ͣ���ͬһ���ļ��еĵ��ʽ������
		int total = 0;
		for(Text v:v21){
			total = total + Integer.parseInt(v.toString());
		}
		
		//k21�ǣ�love:data01.txt
		String data = k21.toString();
		//�ҵ���ð�ŵ�λ��
		int index = data.indexOf(":");
		
		String word = data.substring(0, index);        //����
		String fileName = data.substring(index + 1);   //�ļ���
		
		//�����
		context.write(new Text(word), new Text(fileName+":"+total));
	}
}
















