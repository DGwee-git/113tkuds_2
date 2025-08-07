//練習 2.3：字串遞迴處理進階

import java.util.HashSet;

public class AdvancedStringRecursion {

    // 1. 遞迴產生所有排列組合
    public static void permute(String str) {
        permuteHelper("", str);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permuteHelper(prefix + remaining.charAt(i),
                          remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    // 2. 遞迴字串匹配演算法（檢查 text 是否包含 pattern）
    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.isEmpty()) return false;
        if (text.charAt(0) == pattern.charAt(0)) {
            return match(text.substring(1), pattern.substring(1));
        } else {
            return match(text.substring(1), pattern);
        }
    }

    // 3. 遞迴移除重複字符
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, new HashSet<>(), 0);
    }

    private static String removeDuplicatesHelper(String str, HashSet<Character> seen, int index) {
        if (index == str.length()) return "";
        char c = str.charAt(index);
        if (seen.contains(c)) {
            return removeDuplicatesHelper(str, seen, index + 1);
        } else {
            seen.add(c);
            return c + removeDuplicatesHelper(str, seen, index + 1);
        }
    }

    // 4. 遞迴計算所有子字串
    public static void allSubstrings(String str) {
        allSubstringsHelper(str, 0, 1);
    }

    private static void allSubstringsHelper(String str, int start, int end) {
        if (start == str.length()) return;
        if (end > str.length()) {
            allSubstringsHelper(str, start + 1, start + 2);
        } else {
            System.out.println(str.substring(start, end));
            allSubstringsHelper(str, start, end + 1);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        System.out.println("=== 1. 所有排列組合 ===");
        permute("ABC");

        System.out.println("\n=== 2. 字串匹配 ===");
        System.out.println(match("hello world", "low")); // true
        System.out.println(match("hello world", "hero")); // false

        System.out.println("\n=== 3. 移除重複字符 ===");
        System.out.println(removeDuplicates("banana")); // ban

        System.out.println("\n=== 4. 所有子字串 ===");
        allSubstrings("abc");
    }
}
