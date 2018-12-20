package day0912.sort.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * һ��Ҫ��Emp��Ϊkey2
 * û��value2������nullֵ
 */
public class EmpSortMapper extends Mapper<LongWritable, Text, Emp, NullWritable> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// ���ݣ�7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(",");
		
		//����Ա������
		Emp emp = new Emp();
		emp.setEmpno(Integer.parseInt(words[0]));
		emp.setEname(words[1]);
		emp.setJob(words[2]);
		emp.setMgr(Integer.parseInt(words[3]));
		emp.setHiredate(words[4]);
		emp.setSal(Integer.parseInt(words[5]));
		emp.setComm(Integer.parseInt(words[6]));
		emp.setDeptno(Integer.parseInt(words[7]));
		
		//���Ա������  k2:Ա������            v2����ֵ
		context.write(emp,  NullWritable.get());
	}
}















