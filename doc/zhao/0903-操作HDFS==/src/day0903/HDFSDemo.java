package day0903;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class HDFSDemo {

	@Test
	public void testDownload() throws Exception{
		//��������
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);	
		
		//����һ������������HDFS�ж�ȡ����
		InputStream input = client.open(new Path("/folder111/a.tar.gz"));
		
		//����һ�����������������ص�Ŀ¼
		OutputStream output = new FileOutputStream("d:\\temp\\xyz.tar.gz");
		
		//ʹ�ù�����
		IOUtils.copyBytes(input, output, 1024);
	}
	
	@Test
	public void testDataNode()  throws Exception{
		//��ȡDataNode����Ϣ��α�ֲ��Ļ�����
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ��HDFS�ͻ���
		//FileSystem client = FileSystem.get(conf);		
		DistributedFileSystem fs = (DistributedFileSystem) FileSystem.get(conf);	
		
		//��ȡ���ݽڵ����Ϣ: Stats ---> ͳ����Ϣ
		DatanodeInfo[] list = fs.getDataNodeStats();
		for(DatanodeInfo info:list){
			System.out.println(info.getHostName()+"\t"+ info.getName());
		}
		
		fs.close();
	}
	
	@Test
	public void testFileBlockLocation() throws Exception{
		//��ȡ���ݿ����Ϣ
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//��ȡ�ļ���status��Ϣ
		FileStatus fileStatus = client.getFileStatus(new Path("/folder111/a.tar.gz"));
		
		//��ȡ�ļ������ݿ���Ϣ�����飩
		BlockLocation[] locations = client.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
		/*
		 * α�ֲ��Ļ��������ݿ��������ǣ�1
		 */
		for(BlockLocation blk:locations){
			System.out.println(Arrays.toString(blk.getHosts()) + "\t" + Arrays.toString(blk.getNames()));
		}
		
		client.close();
	}
}





























