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


//����������򣬴�������Topology
public class WordCountTopology {

	public static void main(String[] args) throws Exception {
		TopologyBuilder builder = new TopologyBuilder();
		
		//���������spout���
		builder.setSpout("wordcount_spout", new WordCountSpout());
		
		//��������ĵ��ʲ�ֵ�bolt���,���������
		builder.setBolt("wordcount_split", new WordCountSplitBolt()).shuffleGrouping("wordcount_spout");
		
		//��������ĵ��ʼ�����bolt������ǰ��ֶη���
		builder.setBolt("wordcount_total", new WordCountTotalBolt()).fieldsGrouping("wordcount_split", new Fields("word"));
		
		//��������ĵ�����Bolt�������������浽Redis��ֱ��ʹ��Storm�ṩ��BOlt
		//builder.setBolt("wordcount_redis", createRedisBolt()).shuffleGrouping("wordcount_total");
		
		//��������ĵ�����Bolt�������������浽HDFS���ļ�����ֱ��ʹ��Storm�ṩ��Bolt
		//builder.setBolt("wordcount_hdfs", createHDFSBolt()).shuffleGrouping("wordcount_total");
		
		//��������ĵ�����Bolt�������������浽HBase��
		builder.setBolt("wordcount_hbase", new WordCountHBaseBolt()).shuffleGrouping("wordcount_total");
		
		
		//����һ������Topology
		StormTopology topology = builder.createTopology();
		
		//����һ��Config���󣬱���������Ϣ
		Config conf = new Config();
		
		/*
		 * �ύStorm�����������ַ�ʽ
		 * 1������ģʽ
		 * 2����Ⱥģʽ
		 */
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("MyWordCount", conf, topology);
		
//		StormSubmitter.submitTopology("MyWordCount", conf, topology);

	}

	private static IRichBolt createHDFSBolt() {
		// ��������浽HDFS �ļ�
		
		HdfsBolt bolt = new HdfsBolt();
		//����HDFS�����������Ϣ
		//HDFS��λ�ã�NameNode�ĵ�ַ
		bolt.withFsUrl("hdfs://192.168.157.111:9000");
		
		//���ñ����HDFS��Ŀ¼
		bolt.withFileNameFormat(new DefaultFileNameFormat().withPath("/stormdata"));
		
		//�������<key value>���������ݱ��浽�ļ���ʱ�򣬷ָ��� |
		//������<Beijing,10>   ----> ���: Beijing|10
		bolt.withRecordFormat(new DelimitedRecordFormat().withFieldDelimiter("|"));
		
		//��ʽ����������������һ���ļ�?
		//ÿ5M����������һ���ļ�
		bolt.withRotationPolicy(new FileSizeRotationPolicy(5.0f, Units.MB));
		
		//�����tuple�ﵽ��һ����С���ͻ��HDFS����һ��ͬ��
		bolt.withSyncPolicy(new CountSyncPolicy(1000));
		
		
		return bolt;
	}

	private static IRichBolt createRedisBolt() {
		//�ѵ��ʼ����ǽ�����浽Redis
		
		//����Redis�����ӳ�
		JedisPoolConfig.Builder builder = new JedisPoolConfig.Builder();
		builder.setHost("192.168.157.111");
		builder.setPort(6379);
		JedisPoolConfig poolConfig = builder.build();
		
		//������StoreMapper������ָ������Redis�е����ݸ�ʽ
		return new RedisStoreBolt(poolConfig, new RedisStoreMapper() {
			
			@Override
			public RedisDataTypeDescription getDataTypeDescription() {
				//����Redis�е��������ͣ�WordCount����ʲô�������ͣ�
				//ʹ��Hash����
				return new RedisDataTypeDescription(RedisDataTypeDescription.RedisDataType.HASH,
						                            "wordcount");
			}
			
			@Override
			public String getValueFromTuple(ITuple tuple) {
				// ����һ��Tuple��ȡ��ֵ��Ƶ��
				return String.valueOf(tuple.getIntegerByField("total"));
			}
			
			@Override
			public String getKeyFromTuple(ITuple tuple) {
				// ����һ��Tuple��ȡ��key������
				return tuple.getStringByField("word");
			}
		});
	}
}












