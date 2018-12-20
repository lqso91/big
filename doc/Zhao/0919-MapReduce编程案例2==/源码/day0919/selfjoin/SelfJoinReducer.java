package day0919.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		//����������棺�ϰ��������Ա��������
		String bossName = "";
		String empNameList = "";
		
		for(Text t:v3){
			String str = t.toString();
			
			//�ж��Ƿ����*��
			int index = str.indexOf("*");
			if(index >= 0 ){
				//�ϰ������
				bossName = str.substring(1);
			}else{
				//Ա��������
				empNameList = str + ";" + empNameList;
			}
		}
		
		//�������������ϰ壬Ҳ����Ա�����Ž������
		if(bossName.length() > 0 && empNameList.length() > 0)
			context.write(new Text(bossName), new Text(empNameList));
	}
}


















