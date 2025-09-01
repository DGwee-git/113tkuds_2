import java.util.HashMap;

public class lt_03_LongestSubstring {

    // 回傳結果封裝類 (長度 + 最長子字串)
    static class Result {
        int length;
        String substring;
        Result(int length, String substring) {
            this.length = length;
            this.substring = substring;
        }
    }

    // 演算法：Longest Substring Without Repeating Characters
    public Result longestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        int startIndex = 0; // 記錄最長子字串的起始位置

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, right);

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                startIndex = left;
            }
        }

        String longestSub = s.substring(startIndex, startIndex + maxLen);
        return new Result(maxLen, longestSub);
    }

    // 輸出格式像題目 Example
    public void printExample(String s, int exampleNum) {
        Result res = longestSubstring(s);
        System.out.println("Example " + exampleNum + ":");
        System.out.println("Input: s = \"" + s + "\"");
        System.out.println("Output: " + res.length);
        System.out.println("Explanation: The answer is \"" + res.substring 
                           + "\", with the length of " + res.length + ".");
        System.out.println();
    }

    public static void main(String[] args) {
        lt_03_LongestSubstring solution = new lt_03_LongestSubstring();

        // 題目給的三個例子
        solution.printExample("abcabcbb", 1);
        solution.printExample("bbbbb", 2);
        solution.printExample("pwwkew", 3);

        // 其他隨機測試
        solution.printExample("abcdefg", 4);    // 全部不同字元
        solution.printExample("abba", 5);       // 中間有重複
        solution.printExample("dvdf", 6);       // tricky case
        solution.printExample("", 7);           // 空字串
        solution.printExample("au", 8);         // 短字串
    }
}
