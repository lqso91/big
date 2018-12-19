package day0903;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestUpload {

	@Test
	public void test1() throws Exception{
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);	
		
		//����һ�����������ӱ��ض�������
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
		
		//����һ������� ָ��HDFS
		OutputStream output = client.create(new Path("/folder111/a.tar.gz"));
		
		//����һ��������
		byte[] buffer = new byte[1024];
		//����
		int len = 0;
		
		//��������
		while((len=input.read(buffer)) > 0){
			//д���������
			output.write(buffer, 0, len);
		}
		
		output.flush();
		
		//�ر���
		input.close();
		output.close();
	}
	
	@Test
	public void test2() throws Exception{
		//ָ����ǰ��Hadoop���û�
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//���ò�����ָ��NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//����һ���ͻ���
		FileSystem client = FileSystem.get(conf);	
		
		//����һ�����������ӱ��ض�������
		InputStream input = new FileInputStream("d:\\temp\\hadoop-2.7.3.tar.gz");
		
		//����һ������� ָ��HDFS
		OutputStream output = client.create(new Path("/folder111/b.tar.gz"));
		
		//ʹ��HDFS��һ��������򻯴���
		IOUtils.copyBytes(input, output, 1024);
	}
}




















