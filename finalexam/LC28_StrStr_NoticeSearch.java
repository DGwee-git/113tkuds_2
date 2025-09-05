import java.util.*;

public class LC28_StrStr_NoticeSearch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取全文與關鍵字
        String haystack = sc.nextLine();
        String needle = sc.nextLine();

        int idx = strStr(haystack, needle);

        System.out.println(idx);

        sc.close();
    }

    /**
     * 公告全文檢索：回傳 needle 首次出現於 haystack 的索引
     * 若不存在，回傳 -1
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0; // 題目規定
        if (haystack.length() < needle.length()) return -1;

        // --- 暴力解法 O(n*m) ---
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j = 0;
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return i; // 成功匹配
            }
        }
        return -1;

        // --- 若要用 KMP 解法，改呼叫 kmpSearch(haystack, needle) ---
    }

    /**
     * KMP 演算法實作
     * O(n+m)
     */
    public static int kmpSearch(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] lps = buildLPS(needle); // 最長前後綴表
        int i = 0, j = 0; // i: haystack, j: needle

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) return i - j; // 找到匹配
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    /**
     * 建立 LPS (Longest Prefix Suffix) 陣列
     */
    private static int[] buildLPS(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0; // 最長前後綴長度
        int i = 1;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
