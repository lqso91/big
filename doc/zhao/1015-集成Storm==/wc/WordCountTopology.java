package demo.wc;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy;
import org.apache.storm.hdfs.bolt.rotation.FileSizeRotationPolicy.Units;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.redis.bolt.RedisStoreBolt;
import org.apache.storm.redis.common.config.JedisPoolConfig;
import org.apache.storm.redis.common.mapper.RedisDataTypeDescription;
import org.apache.storm.redis.common.mapper.RedisStoreMapper;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.ITuple;


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
		
		//设置任务的第三个Bolt组件，将结果保存到Redis，直接使用Storm提供的BOlt
		//builder.setBolt("wordcount_redis", createRedisBolt()).shuffleGrouping("wordcount_total");
		
		//设置任务的第三个Bolt组件，将结果保存到HDFS（文件），直接使用Storm提供的Bolt
		//builder.setBolt("wordcount_hdfs", createHDFSBolt()).shuffleGrouping("wordcount_total");
		
		//设置任务的第三个Bolt组件，将结果保存到HBase中
		builder.setBolt("wordcount_hbase", new WordCountHBaseBolt()).shuffleGrouping("wordcount_total");
		
		
		//创建一个任务：Topology
		StormTopology topology = builder.createTopology();
		
		//创建一个Config对象，保存配置信息
		Config conf = new Config();
		
		/*
		 * 提交Storm的任务有两种方式
		 * 1、本地模式
		 * 2、集群模式
		 */
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("MyWordCount", conf, topology);
		
//		StormSubmitter.submitTopology("MyWordCount", conf, topology);

	}

	private static IRichBolt createHDFSBolt() {
		// 将结果保存到HDFS 文件
		
		HdfsBolt bolt = new HdfsBolt();
		//设置HDFS的相关配置信息
		//HDFS的位置：NameNode的地址
		bolt.withFsUrl("hdfs://192.168.157.111:9000");
		
		//设置保存的HDFS的目录
		bolt.withFileNameFormat(new DefaultFileNameFormat().withPath("/stormdata"));
		
		//保存的是<key value>，设置数据保存到文件的时候，分隔符 |
		//举例：<Beijing,10>   ----> 结果: Beijing|10
		bolt.withRecordFormat(new DelimitedRecordFormat().withFieldDelimiter("|"));
		
		//流式处理，多大的数据生成一个文件?
		//每5M的数据生成一个文件
		bolt.withRotationPolicy(new FileSizeRotationPolicy(5.0f, Units.MB));
		
		//当输出tuple达到了一定大小，就会跟HDFS进行一次同步
		bolt.withSyncPolicy(new CountSyncPolicy(1000));
		
		
		return bolt;
	}

	private static IRichBolt createRedisBolt() {
		//把单词计数是结果保存到Redis
		
		//创建Redis的连接池
		JedisPoolConfig.Builder builder = new JedisPoolConfig.Builder();
		builder.setHost("192.168.157.111");
		builder.setPort(6379);
		JedisPoolConfig poolConfig = builder.build();
		
		//参数：StoreMapper：用于指定存入Redis中的数据格式
		return new RedisStoreBolt(poolConfig, new RedisStoreMapper() {
			
			@Override
			public RedisDataTypeDescription getDataTypeDescription() {
				//定义Redis中的数据类型：WordCount采用什么数据类型？
				//使用Hash集合
				return new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH,
						                            "wordcount");
			}
			
			@Override
			public String getValueFromTuple(ITuple tuple) {
				// 从上一个Tuple中取出值：频率
				return String.valueOf(tuple.getIntegerByField("total"));
			}
			
			@Override
			public String getKeyFromTuple(ITuple tuple) {
				// 从上一个Tuple中取出key：单词
				return tuple.getStringByField("word");
			}
		});
	}
}












