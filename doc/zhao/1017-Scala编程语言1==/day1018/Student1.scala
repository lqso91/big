package day1018

//����һ��ѧ������Ϣ
class Student1 {
  //����ѧ��������
  private var stuID:Int = 0
  private var stuName:String = "Tom"
  private var age:Int = 20
  
  //�����Ա������������:get ��set
  //�������ֺ������get��set  
  def getStuName():String = stuName
  def setStuName(newName:String) = this.stuName = newName
  
  def getStuAge():Int = age
  def setStuAge(newAge:Int) = this.age = newAge
}

//����Student1�࣬����main����(д��Object��)
//ע�⣺object��class�����ֿ��Բ�һ�������һ���ˣ����object�ͽ�����class�İ�������
object Student1{
  def main(args: Array[String]): Unit = {
    //����ѧ������
    var s1 = new Student1
    
    //��һ�η������Բ����
    println(s1.getStuName()+"\t"+s1.getStuAge())
    
    //����set����
    s1.setStuName("Mary")
    s1.setStuAge(22)
    println(s1.getStuName()+"\t"+s1.getStuAge())
    
    //�����һ�Σ�ֱ�ӷ���˽�е�����
    println("*************ֱ�ӷ���˽�е�����************")
    println(s1.stuID +"\t"+ s1.stuName+"\t"+s1.age)
    
    //ע�⣺����ֱ�ӷ������˽�г�Ա  Ϊʲô���ԣ� ----> ���Ե�get��set����
    
  }
}














