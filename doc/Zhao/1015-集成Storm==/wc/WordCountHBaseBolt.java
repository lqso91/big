package demo.wc;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/*
 * 建表:
 * create 'result','info'
 */
public class WordCountHBaseBolt extends BaseRichBolt {

	//定义一个Hbase的表的客户端
	private HTable table = null;
	
	@Override
	public void execute(Tuple tuple) {
		// 把上一个组件发来的数据存入HBase
		String word = tuple.getStringByField("word");
		int total = tuple.getIntegerByField("total");
		
		//构造一个Put对象
		Put put = new Put(Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("word"), Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("total"), Bytes.toBytes(String.valueOf(total)));
		
		//插入HBase
		try {
			table.put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		//对该组件进行初始化
		//指定ZK的地址
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//创建HTable的客户端
		try {
			table = new HTable(conf, "result");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}
}












