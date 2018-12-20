package day0910.serializable.salarytotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class SalaryTotalReducer extends Reducer<IntWritable, Emp, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Emp> v3,Context context) throws IOException, InterruptedException {
		int total = 0;
		
		//取出员工薪水，并求和
		for(Emp e:v3){
			total = total + e.getSal();
		}
		
		context.write(k3, new IntWritable(total));
	}
}
