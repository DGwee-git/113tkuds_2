import java.util.Stack;

public class lt_32_LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 基準索引
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "(()";
        System.out.println(s1 + " -> " + longestValidParentheses(s1)); // 2

        String s2 = ")()())";
        System.out.println(s2 + " -> " + longestValidParentheses(s2)); // 4

        String s3 = "";
        System.out.println(s3 + " -> " + longestValidParentheses(s3)); // 0
    }
}
