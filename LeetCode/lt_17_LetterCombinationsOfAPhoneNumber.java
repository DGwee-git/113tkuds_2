import java.util.*;

public class lt_17_LetterCombinationsOfAPhoneNumber {
    // 電話按鍵對應表
    private static final Map<Character, String> phoneMap = new HashMap<>();
    static {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    // 回溯法產生所有組合
    private static void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = phoneMap.get(digits.charAt(index));
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }

    // 測試 main
    public static void main(String[] args) {
        String digits1 = "23";
        System.out.println("Input: " + digits1);
        System.out.println("Output: " + letterCombinations(digits1));

        String digits2 = "";
        System.out.println("Input: " + digits2);
        System.out.println("Output: " + letterCombinations(digits2));

        String digits3 = "2";
        System.out.println("Input: " + digits3);
        System.out.println("Output: " + letterCombinations(digits3));
    }
}
