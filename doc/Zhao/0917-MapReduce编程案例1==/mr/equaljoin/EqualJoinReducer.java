package day0917.mr.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EqualJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		// ����v3�������ǲ������ơ�Ҳ������Ա��������
		String dname = "";
		String empNameList = "";
		
		for(Text value:v3){
			String str = value.toString();
			//�ж��Ƿ����*
			int index = str.indexOf("*");
			if(index >= 0){
				//�����ǲ��ŵ�����
				dname = str.substring(1);
			}else{
				//������Ա��������
				empNameList = str + ";" + empNameList;
			}
		}
		
		//���
		context.write(new Text(dname), new Text(empNameList));
	}

}










