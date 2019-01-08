package top.lqso.code.chapter1;

/**
 * 1.2 浮点数
 *
 * @author luojie
 * @date 2019-1-5
 */
public class Float {
    public static void main(String[] args) {
        float a = 1f;
        float b = 0.9f;

        float f = a - b;
        // 0.100000024
        System.out.println(f);
    }
}
/*
浮点数取值范围：
单精度类型   4字节     1.4e-45至3.4e+38         -3.4e+38至-1.4e-45
双精度类型   8字节     4.9e-324至1.798e+308     -1.798e+308至-4.9e-324

单精度浮点数格式：
符号      指数      有效数字
符号位    阶码位    尾数位
1位       8位       23位
 */