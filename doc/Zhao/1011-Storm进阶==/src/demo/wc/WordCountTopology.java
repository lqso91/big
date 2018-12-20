package demo.wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

//任务的主程序，创建任务：Topology
public class WordCountTopology {

	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		
		//设置任务的spout组件
		builder.setSpout("wordcount_spout", new WordCountSpout());
		
		//设置任务的单词拆分的bolt组件,是随机分组
		builder.setBolt("wordcount_split", new WordCountSplitBolt()).shuffleGrouping("wordcount_spout");
		
		//设置任务的单词计数的bolt组件，是按字段分组
		builder.setBolt("wordcount_total", new WordCountTotalBolt()).fieldsGrouping("wordcount_split", new Fields("word"));
		
		//创建一个任务：Topology
		StormTopology topology = builder.createTopology();
		
		//创建一个Config对象，保存配置信息
		Config conf = new Config();
		
		/*
		 * 提交Storm的任务有两种方式
		 * 1、本地模式
		 * 2、集群模式
		 */
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("MyWordCount", conf, topology);
		
		StormSubmitter.submitTopology("MyWordCount", conf, topology);

	}

}












