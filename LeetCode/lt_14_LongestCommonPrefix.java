public class lt_14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String prefix = strs[0]; // 先假設第一個字串是共同前綴
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1); // 不斷縮短
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        lt_14_LongestCommonPrefix solution = new lt_14_LongestCommonPrefix();

        String[][] tests = {
            {"flower","flow","flight"},
            {"dog","racecar","car"},
            {"interspecies","interstellar","interstate"},
            {"throne","throne"},
            {"","b"}
        };

        for (String[] strs : tests) {
            System.out.print("Input: ");
            for (String s : strs) System.out.print(s + " ");
            System.out.println("\nOutput: " + solution.longestCommonPrefix(strs));
            System.out.println();
        }
    }
}
