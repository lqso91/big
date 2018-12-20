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
 * ����:
 * create 'result','info'
 */
public class WordCountHBaseBolt extends BaseRichBolt {

	//����һ��Hbase�ı�Ŀͻ���
	private HTable table = null;
	
	@Override
	public void execute(Tuple tuple) {
		// ����һ��������������ݴ���HBase
		String word = tuple.getStringByField("word");
		int total = tuple.getIntegerByField("total");
		
		//����һ��Put����
		Put put = new Put(Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("word"), Bytes.toBytes(word));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("total"), Bytes.toBytes(String.valueOf(total)));
		
		//����HBase
		try {
			table.put(put);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		//�Ը�������г�ʼ��
		//ָ��ZK�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "192.168.157.111");
		
		//����HTable�Ŀͻ���
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












