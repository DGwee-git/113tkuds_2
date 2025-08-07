//練習 2.4：遞迴與迭代比較

public class RecursionVsIteration {

    /* ==================== 1. 計算二項式係數 ==================== */

    // 遞迴版本
    public static long binomialCoefficientRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialCoefficientRec(n - 1, k - 1) + binomialCoefficientRec(n - 1, k);
    }

    // 迭代版本（使用動態規劃）
    public static long binomialCoefficientIter(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    /* ==================== 2. 陣列所有元素乘積 ==================== */

    // 遞迴版本
    public static long arrayProductRec(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * arrayProductRec(arr, index + 1);
    }

    // 迭代版本
    public static long arrayProductIter(int[] arr) {
        long product = 1;
        for (int num : arr) product *= num;
        return product;
    }

    /* ==================== 3. 計算字串元音數量 ==================== */

    // 遞迴版本
    public static int vowelCountRec(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + vowelCountRec(str, index + 1);
    }

    // 迭代版本
    public static int vowelCountIter(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    /* ==================== 4. 檢查括號配對 ==================== */

    // 遞迴版本（用計數器模擬）
    public static boolean parenthesesMatchRec(String str, int index, int balance) {
        if (balance < 0) return false;
        if (index == str.length()) return balance == 0;
        char c = str.charAt(index);
        if (c == '(') return parenthesesMatchRec(str, index + 1, balance + 1);
        if (c == ')') return parenthesesMatchRec(str, index + 1, balance - 1);
        return parenthesesMatchRec(str, index + 1, balance);
    }

    // 迭代版本
    public static boolean parenthesesMatchIter(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    /* ==================== 效能比較 ==================== */

    public static void main(String[] args) {
        System.out.println("=== 遞迴與迭代比較 ===");

        // 測試資料
        int n = 20, k = 10;
        int[] arr = {1, 2, 3, 4, 5};
        String str = "Hello World (OpenAI)";
        String brackets = "(())()";

        /* 1. 二項式係數 */
        long start = System.nanoTime();
        long binRec = binomialCoefficientRec(n, k);
        long end = System.nanoTime();
        System.out.println("遞迴 C(" + n + "," + k + ") = " + binRec + "，耗時: " + (end - start) + " ns");

        start = System.nanoTime();
        long binIter = binomialCoefficientIter(n, k);
        end = System.nanoTime();
        System.out.println("迭代 C(" + n + "," + k + ") = " + binIter + "，耗時: " + (end - start) + " ns");

        /* 2. 陣列乘積 */
        start = System.nanoTime();
        long prodRec = arrayProductRec(arr, 0);
        end = System.nanoTime();
        System.out.println("遞迴 乘積 = " + prodRec + "，耗時: " + (end - start) + " ns");

        start = System.nanoTime();
        long prodIter = arrayProductIter(arr);
        end = System.nanoTime();
        System.out.println("迭代 乘積 = " + prodIter + "，耗時: " + (end - start) + " ns");

        /* 3. 元音數量 */
        start = System.nanoTime();
        int vowelsRec = vowelCountRec(str, 0);
        end = System.nanoTime();
        System.out.println("遞迴 元音數量 = " + vowelsRec + "，耗時: " + (end - start) + " ns");

        start = System.nanoTime();
        int vowelsIter = vowelCountIter(str);
        end = System.nanoTime();
        System.out.println("迭代 元音數量 = " + vowelsIter + "，耗時: " + (end - start) + " ns");

        /* 4. 括號配對 */
        start = System.nanoTime();
        boolean parenRec = parenthesesMatchRec(brackets, 0, 0);
        end = System.nanoTime();
        System.out.println("遞迴 括號配對 = " + parenRec + "，耗時: " + (end - start) + " ns");

        start = System.nanoTime();
        boolean parenIter = parenthesesMatchIter(brackets);
        end = System.nanoTime();
        System.out.println("迭代 括號配對 = " + parenIter + "，耗時: " + (end - start) + " ns");
    }
}
