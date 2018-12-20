package day0912.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//�Զ���ķ������򣺰��ղ��źŽ��з���                                              k2 ���ź�                     v2  Ա������
public class MyPartitioner extends Partitioner<IntWritable, Emp> {

	/**
	 * numTask�������ĸ���
	 */
	@Override
	public int getPartition(IntWritable k2, Emp v2, int numTask) {
		// �������ǵķ�������
		//�õ���Ա���Ĳ��ź�
		int deptno = v2.getDeptno();
		
		if(deptno == 10){
			//����һ�ŷ���
			return 1%numTask;
		}else if(deptno == 20){
			//������ŷ���
			return 2%numTask;
		}else{
			//30�Ų��ţ�������ŷ���
			return 3%numTask;
		}
	}

}
