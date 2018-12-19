package demo;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.junit.Test;

import net.spy.memcached.MemcachedClient;

public class TestMemcached {

	@Test
	public void testInsert() throws Exception{
		//����һ���ַ���
		//�õ��ͻ���
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("192.168.157.111", 11211));
		Future<Boolean> f = client.set("key1", 0, "Hello World");
		if(f.get().booleanValue()){
			//��ʾ�ɹ�
			client.shutdown();
		}
	}
	
	@Test
	public void testInsertObjext() throws Exception{
		//����һ������
		//��������
		Student s = new Student();
		
		//�õ��ͻ���
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("192.168.157.111", 11211));
		Future<Boolean> f = client.set("s001", 0, s);
		if(f.get().booleanValue()){
			//��ʾ�ɹ�
			client.shutdown();
		}		
	}
	
	@Test
	public void testGet() throws Exception{
		//��ѯ
		//�õ��ͻ���
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("192.168.157.111", 11211));
		Object value = client.get("key1");
		System.out.println(value);
		client.shutdown();
	}
	
	//����MemCached�Ŀͻ���·��
	@Test
	public void testInsertList() throws Exception{
		//��ѯ
		//����һ��List�������е�MemCachedʵ��
		List<InetSocketAddress> list = new ArrayList<>();
		list.add(new InetSocketAddress("192.168.157.111", 11211));
		list.add(new InetSocketAddress("192.168.157.111", 11212));
		list.add(new InetSocketAddress("192.168.157.111", 11213));
			
		MemcachedClient client = new MemcachedClient(list);
		
		//����20������
		for(int i=0;i<20;i++){
			System.out.println("����������ǣ� key="+("key"+i)+"     value��"+("value"+i));
			client.set("key"+i, 0, "value"+i);
			
			//˯1��
			Thread.sleep(1000);
		}
		
		//�رտͻ���
		client.shutdown();
	}	
}

//����һ����
class Student implements Serializable{
	
}




























