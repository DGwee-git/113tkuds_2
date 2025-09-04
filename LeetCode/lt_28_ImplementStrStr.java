public class lt_28_ImplementStrStr {
    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int n = haystack.length();
        int m = needle.length();

        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String haystack1 = "hello", needle1 = "ll";
        String haystack2 = "aaaaa", needle2 = "bba";
        String haystack3 = "abc", needle3 = "";

        System.out.println("\"" + haystack1 + "\", \"" + needle1 + "\" -> " + strStr(haystack1, needle1)); // 2
        System.out.println("\"" + haystack2 + "\", \"" + needle2 + "\" -> " + strStr(haystack2, needle2)); // -1
        System.out.println("\"" + haystack3 + "\", \"" + needle3 + "\" -> " + strStr(haystack3, needle3)); // 0
    }
}
