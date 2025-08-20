import java.util.Scanner;

public class M06_PalindromeClean {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入要檢測的字串: ");
        // 讀取一行字串
        String input = sc.nextLine();
        
        // 方法1：使用雙指標直接檢測（推薦，空間效率更高）
        boolean isPalindrome = isPalindromeWithTwoPointers(input);
        
        // 方法2：先清洗字串再遞迴檢測（備選方案）
        // boolean isPalindrome = isPalindromeWithCleanString(input);
        
        // 輸出結果
        System.out.println(isPalindrome ? "Yes" : "No");
        
        sc.close();
    }
    
    /**
     * 方法1：使用雙指標直接檢測回文（推薦）
     * 不需要額外空間，直接跳過非字母字符
     * 
     * @param s 待檢測的字串
     * @return 是否為回文
     */
    public static boolean isPalindromeWithTwoPointers(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // 左指標跳過非字母字符
            while (left < right && !isAlphabetic(s.charAt(left))) {
                left++;
            }
            
            // 右指標跳過非字母字符
            while (left < right && !isAlphabetic(s.charAt(right))) {
                right--;
            }
            
            // 比較當前字符（轉為小寫）
            if (toLowerCase(s.charAt(left)) != toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * 方法2：先清洗字串再遞迴檢測
     * 將字串清洗為只含 a-z 的小寫字母，然後遞迴判斷
     * 
     * @param s 待檢測的字串
     * @return 是否為回文
     */
    public static boolean isPalindromeWithCleanString(String s) {
        // 清洗字串：只保留英文字母並轉為小寫
        String cleaned = cleanString(s);
        
        // 遞迴檢測回文
        return isPalindromeRecursive(cleaned, 0, cleaned.length() - 1);
    }
    
    /**
     * 清洗字串：移除非英文字母並轉為小寫
     * @param s 原始字串
     * @return 清洗後的字串
     */
    public static String cleanString(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isAlphabetic(c)) {
                sb.append(toLowerCase(c));
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 遞迴檢測回文
     * @param s 已清洗的字串
     * @param left 左邊界
     * @param right 右邊界
     * @return 是否為回文
     */
    public static boolean isPalindromeRecursive(String s, int left, int right) {
        // 終止條件：指標相遇或交錯
        if (left >= right) {
            return true;
        }
        
        // 比較當前字符
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        
        // 遞迴檢查剩餘部分
        return isPalindromeRecursive(s, left + 1, right - 1);
    }
    
    /**
     * 檢查字符是否為英文字母
     * @param c 待檢查的字符
     * @return 是否為英文字母
     */
    public static boolean isAlphabetic(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
    
    /**
     * 將字符轉為小寫
     * @param c 待轉換的字符
     * @return 小寫字符
     */
    public static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c - 'A' + 'a');
        }
        return c;
    }
    
    /**
     * 迭代版本的回文檢測（供參考）
     * @param s 已清洗的字串
     * @return 是否為回文
     */
    public static boolean isPalindromeIterative(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * 測試函式：驗證不同輸入的結果
     */
    public static void testCases() {
        String[] testInputs = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "",
            "a",
            "Madam",
            "No 'x' in Nixon",
            "Mr. Owl ate my metal worm"
        };
        
        for (String input : testInputs) {
            boolean result1 = isPalindromeWithTwoPointers(input);
            boolean result2 = isPalindromeWithCleanString(input);
            System.out.println("Input: \"" + input + "\"");
            System.out.println("雙指標結果: " + (result1 ? "Yes" : "No"));
            System.out.println("清洗後結果: " + (result2 ? "Yes" : "No"));
            System.out.println("結果一致: " + (result1 == result2));
            System.out.println();
        }
    }
}

