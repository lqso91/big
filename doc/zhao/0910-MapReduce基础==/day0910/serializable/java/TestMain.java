package day0910.serializable.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// ����һ��ѧ������
		Student s = new Student();
		s.setStuID(1);
		s.setStuName("Tom");
		
		//��������󱣴浽�ļ��� -----> ���л�
		OutputStream out = new FileOutputStream("d:\\temp\\student.ooo");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		
		oos.writeObject(s);
		
		oos.close();
		out.close();
	}
}
