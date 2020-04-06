package easy;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * @author lqso 2019年12月18日13:25:49
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(120));
    }

    public int reverse(int x) {
        boolean sign = x >= 0;
        int y = x >= 0 ? x : -x;
        int i = 0;

        int[] arr = new int[10];
        while (y > 0){
            arr[i++] = y % 10;
            y /= 10;
        }

        long r = 0;
        long p = 1;

        for (int j = i - 1; j >= 0; j--) {
            r += arr[j] * p;
            p *= 10;
        }

        r = sign ? r : -r;
        r = (r < -(1L << 31)) || r > (1L << 31) - 1 ? 0 : r;

        return (int)r;
    }
}
