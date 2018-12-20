package day0905.rpc.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class MyRPCServer {

	public static void main(String[] args) throws Exception {
		// ����Hadoop RPC�Ŀ��ʵ��RPC Server
		
		//ʹ��RPC Builder������ 
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		//����Server�Ĳ���
		builder.setBindAddress("localhost");
		builder.setPort(7788);
		
		//�������ǵĳ���
		builder.setProtocol(MyInterface.class); //����Ľӿ�
		builder.setInstance(new MyInterfaceImpl()); //ָ���ӿڵ�ʵ����
		
		// ����RPC Server
		Server server = builder.build();
		
		//����Server
		server.start();
	}

}















