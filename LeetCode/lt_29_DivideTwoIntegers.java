public class lt_29_DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Division by zero");
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        boolean negative = (dividend < 0) ^ (divisor < 0); // 判斷結果是否為負數

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;

        while (a >= b) {
            long temp = b;
            int multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println("10 / 3 = " + divide(10, 3));   // 3
        System.out.println("7 / -3 = " + divide(7, -3));   // -2
        System.out.println("-15 / 2 = " + divide(-15, 2)); // -7
        System.out.println("1 / 1 = " + divide(1, 1));     // 1
        System.out.println("-2147483648 / -1 = " + divide(Integer.MIN_VALUE, -1)); // 2147483647 (overflow)
    }
}
