package day0905.rpc.client;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import day0905.rpc.server.MyInterface;

public class MyRPCClient {

	public static void main(String[] args) throws Exception {
		// ʹ��Hadoop RPC�Ŀ�ܵ���Server �˵ĳ���
		/*
		RPC.getProxy(protocol,    ���õĽӿ�
				     clientVersion, �汾��
				     addr,   RPC Server�ĵ�ַ
				     conf)
		*/

		//�õ�����Server�˲������Ĵ������
		MyInterface proxy = RPC.getProxy(MyInterface.class, 
							             MyInterface.versionID, 
							             new InetSocketAddress("localhost", 7788), 
							             new Configuration());
		
		//ʹ���������������Server�ĳ���
		String result = proxy.sayHello("Tom");
		System.out.println(result);
	}
}













