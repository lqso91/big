package day0905.rpc.server;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyInterfaceImpl implements MyInterface {

	@Override
	public String sayHello(String name) {
		System.out.println("*********���õ���Server ��**********");
		return "Hello "+name;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		//ͨ���汾�Ŷ���ǩ����Ϣ
		return new ProtocolSignature(MyInterface.versionID, null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		//���ذ汾��
		return MyInterface.versionID;
	}
}
