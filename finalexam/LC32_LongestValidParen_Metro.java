import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        String s = scanner.nextLine();
        
        // 計算最長有效片段長度
        int result = longestValidParentheses(s);
        
        // 輸出結果
        System.out.println(result);
        
        scanner.close();
    }
    
    /**
     * 使用索引堆疊方法找最長有效括號片段
     * @param s 進出站記錄字串
     * @return 最長合法配對的長度
     */
    public static int longestValidParentheses(String s) {
        // 邊界案例：空字串
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // 使用堆疊存儲索引
        Stack<Integer> stack = new Stack<>();
        
        // 初始化：棧底放入 -1 作為基準索引
        stack.push(-1);
        
        int maxLength = 0;
        
        // 遍歷每個字元
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // 進站事件：將索引入棧
                stack.push(i);
            } else { // c == ')'
                // 出站事件：先彈出棧頂
                stack.pop();
                
                if (stack.isEmpty()) {
                    // 棧空了，說明當前 ')' 無法配對
                    // 將當前索引作為新的基準點
                    stack.push(i);
                } else {
                    // 棧不空，計算當前有效片段長度
                    // 長度 = 當前索引 - 新的棧頂索引
                    int currentLength = i - stack.peek();
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }
        
        return maxLength;
    }
    
    /**
     * 雙向計數法的替代解法（用於驗證）
     * @param s 進出站記錄字串
     * @return 最長合法配對的長度
     */
    public static int longestValidParenthesesTwoPass(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int maxLength = 0;
        
        // 左到右掃描
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            if (left == right) {
                // 配對成功，更新最大長度
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                // 出站事件過多，重置計數器
                left = right = 0;
            }
        }
        
        // 右到左掃描
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            
            if (left == right) {
                // 配對成功，更新最大長度
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                // 進站事件過多，重置計數器
                left = right = 0;
            }
        }
        
        return maxLength;
    }
}