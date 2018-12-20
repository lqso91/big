package day0903;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;


/*
 * ����
 * Permission denied: user=lenovo, access=WRITE, inode="/folder111":root:supergroup:d rwx  r-x  r-x
 * 
 * ��������û���û��w��Ȩ��
 * 
 * ���ַ�ʽ���Ըı�HDFS��Ȩ�ޣ�
 * 
 * ��һ�ַ�ʽ�����û�������  HADOOP_USER_NAME = root
 * �ڶ��ַ�ʽ��ͨ��ʹ��Java�� -D����
 * �����ַ�ʽ��dfs.permissions ----> false
 * �����ַ�ʽ������ -chmod �ı�HDFSĿ¼��Ȩ��
 */
public class TestMkDir {

	@Test
	public void testMkDir1() throws Exception{
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder111"));
		
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void testMkDir2() throws Exception{
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder222"));
		
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void testMkDir4() throws Exception{
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//����Ŀ¼
		client.mkdirs(new Path("/folder222/folder444"));
		
		//�رտͻ���
		client.close();
	}
}















