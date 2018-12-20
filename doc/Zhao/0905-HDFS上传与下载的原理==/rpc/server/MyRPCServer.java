package day0905.rpc.server;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class MyRPCServer {

	public static void main(String[] args) throws Exception {
		// 利用Hadoop RPC的框架实现RPC Server
		
		//使用RPC Builder来构建 
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		//定义Server的参数
		builder.setBindAddress("localhost");
		builder.setPort(7788);
		
		//部署我们的程序
		builder.setProtocol(MyInterface.class); //部署的接口
		builder.setInstance(new MyInterfaceImpl()); //指定接口的实现类
		
		// 创建RPC Server
		Server server = builder.build();
		
		//启动Server
		server.start();
	}

}















