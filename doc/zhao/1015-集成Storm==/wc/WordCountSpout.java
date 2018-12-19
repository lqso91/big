package demo.wc;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

//��һ���������Ϊ�����Spout��������ɼ�����
//ģ��һЩ���ݣ������������
public class WordCountSpout extends BaseRichSpout {

	//��������Ҫ����������
	private String[] datas = {"I love Beijing","I love China","Beijing is the capital of China"};

	//����һ�����������������
	private SpoutOutputCollector collector;
	
	@Override
	public void nextTuple() {
		//ÿ��2��ɼ�һ��
		Utils.sleep(2000);
		
		// ��Storm�Ŀ�ܵ��ã�������ν�������
		//����һ��3���ڵ������
		int random = (new Random()).nextInt(3);
		//����
		String data = datas[random];
		
		//�����ݷ��͸���һ�����
		//����һ��Ҫ��ѭschema�Ľṹ
		System.out.println("�ɼ��������ǣ�" + data);
		this.collector.emit(new Values(data));
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
		//�൱��Spout��ʼ������
		//������SpoutOutputCollector collector �൱���������
		this.collector = collector;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		// ����Tuple�ĸ�ʽ����Schema
		declare.declare(new Fields("sentence"));
	}
}



























