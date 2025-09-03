public class lt_14_LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        lt_14_LongestCommonPrefix solution = new lt_14_LongestCommonPrefix();
        String[] strs1 = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        System.out.println(solution.longestCommonPrefix(strs1)); // 預期 "fl"
        System.out.println(solution.longestCommonPrefix(strs2)); // 預期 ""
    }
}

/*
解題思路：
- 假設第一個字串是前綴。
- 對比後續字串，不符合時縮短 prefix。
- 直到符合或變空。
*/
