public class lt_07_ReverseInteger {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // 檢查溢出
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) return 0;

            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        lt_07_ReverseInteger solution = new lt_07_ReverseInteger();

        int[] tests = {123, -123, 120, 1534236469}; // 最後一個測試會溢出
        for (int x : tests) {
            System.out.println("Input: x = " + x);
            System.out.println("Output: " + solution.reverse(x));
            System.out.println();
        }
    }
}
