package demo.wc;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

//�ڶ����������bolt��������ڵ��ʵĲ��
public class WordCountSplitBolt extends BaseRichBolt{

	private OutputCollector collector;
	
	@Override
	public void execute(Tuple tuple) {
		//��δ�����һ���������������: I love Beijing
		String data = tuple.getStringByField("sentence");
		
		//�ִ�
		String[] words = data.split(" ");
		
		//���
		for(String w:words){
			collector.emit(new Values(w,1));
		}
	}

	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
		// ��Bolt���г�ʼ��
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		//����Tuple�ĸ�ʽ
		declare.declare(new Fields("word","count"));
		
	}
}










