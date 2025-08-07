//練習 2.1：遞迴數學計算器

public class RecursiveMathCalculator {

    // 1. 計算組合數 C(n, k)
    public static long combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 計算卡塔蘭數 Catalan(n)
    public static long catalan(int n) {
        if (n <= 1) return 1;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 漢諾塔移動步數 hanoi(n)
    public static long hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 4. 判斷是否為回文數
    public static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    // 測試主程式
    public static void main(String[] args) {
        System.out.println("=== 遞迴數學計算器 ===");

        // 測試組合數
        System.out.println("C(5, 2) = " + combination(5, 2)); // 10

        // 測試卡塔蘭數
        System.out.println("Catalan(4) = " + catalan(4)); // 14

        // 測試漢諾塔
        System.out.println("Hanoi(3) 移動步數 = " + hanoiMoves(3)); // 7

        // 測試回文數
        int num1 = 12321, num2 = 12345;
        System.out.println(num1 + " 是否為回文數: " + isPalindrome(num1));
        System.out.println(num2 + " 是否為回文數: " + isPalindrome(num2));
    }
}
