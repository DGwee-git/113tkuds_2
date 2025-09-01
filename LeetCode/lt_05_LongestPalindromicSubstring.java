public class lt_05_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);     // 奇數長度回文
            int len2 = expandFromCenter(s, i, i + 1); // 偶數長度回文
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 回文長度
    }

    public static void main(String[] args) {
        lt_05_LongestPalindromicSubstring solution = new lt_05_LongestPalindromicSubstring();

        System.out.println("Example 1:");
        String s1 = "babad";
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: \"" + solution.longestPalindrome(s1) + "\"");
        System.out.println("Explanation: \"aba\" is also a valid answer.\n");

        System.out.println("Example 2:");
        String s2 = "cbbd";
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: \"" + solution.longestPalindrome(s2) + "\"\n");
    }
}
