package day0907.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMain {

	public static void main(String[] args) {
		// ��������������
		MyBusiness obj = new MyBusinessImpl();
		
		//Ϊ������󴴽�һ���������
		/*
	public static Object Proxy.newProxyInstance(ClassLoader loader,  �������
                                                Class<?>[] interfaces, ��������ʵ�ֵĽӿ�
                                                InvocationHandler h) ʵ�ֽӿ�������ͻ��˵ĵ���
                               throws IllegalArgumentException
		 */
		
		MyBusiness proxy = (MyBusiness) Proxy.newProxyInstance(TestMain.class.getClassLoader(), 
				                                               obj.getClass().getInterfaces(), 
				                                               new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//����ͻ��˵ĵ���
				if(method.getName().equals("method1")){
					//ִ����д
					System.out.println("***********��������е�method1**************");
					return null;
				}else{
					//��������
					return method.invoke(obj, args);
				}
			}
		});
		
		//ͨ���������ȥ���������Ķ���
		proxy.method1();
		proxy.method2();
	}

}
















