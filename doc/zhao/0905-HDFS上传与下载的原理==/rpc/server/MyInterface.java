package day0905.rpc.server;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyInterface extends VersionedProtocol{

	//����һ���汾��
	//ʹ�ð汾��������ǩ��
	public static long versionID = 1;
	
	//��������ҵ�񷽷�
	public String sayHello(String name);
}
