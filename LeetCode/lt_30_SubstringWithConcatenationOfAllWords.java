import java.util.*;

public class lt_30_SubstringWithConcatenationOfAllWords {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return res;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < words.length) {
                String sub = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                if (!wordCount.containsKey(sub)) break;
                seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                if (seen.get(sub) > wordCount.get(sub)) break;
                j++;
            }
            if (j == words.length) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo","bar"};
        System.out.println(findSubstring(s1, words1)); // [0,9]

        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","word"};
        System.out.println(findSubstring(s2, words2)); // []

        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar","foo","the"};
        System.out.println(findSubstring(s3, words3)); // [6,9,12]
    }
}
