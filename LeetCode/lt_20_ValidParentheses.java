import java.util.*;

public class lt_20_ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 左括號入棧
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 右括號 -> 檢查是否能匹配
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty(); // 棧必須清空才算合法
    }

    // 測試用 main
    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";

        System.out.println(s1 + " -> " + isValid(s1)); // true
        System.out.println(s2 + " -> " + isValid(s2)); // true
        System.out.println(s3 + " -> " + isValid(s3)); // false
        System.out.println(s4 + " -> " + isValid(s4)); // false
        System.out.println(s5 + " -> " + isValid(s5)); // true
    }
}
