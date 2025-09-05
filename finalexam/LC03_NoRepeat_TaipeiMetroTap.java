import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        String s = scanner.nextLine();
        
        // 計算最長無重複片段長度
        int result = lengthOfLongestSubstring(s);
        
        // 輸出結果
        System.out.println(result);
        
        scanner.close();
    }
    
    /**
     * 使用滑動視窗解決最長無重複子字串問題
     * @param s 刷卡流水字串
     * @return 最長無重複片段的長度
     */
    public static int lengthOfLongestSubstring(String s) {
        // 邊界案例：空字串
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // Map 記錄每個字元最後出現的索引位置
        Map<Character, Integer> charIndexMap = new HashMap<>();
        
        int left = 0;           // 滑動視窗左邊界
        int maxLength = 0;      // 記錄最長長度
        
        // 右指針向右擴展
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // 如果當前字元之前出現過，且在當前視窗內
            if (charIndexMap.containsKey(currentChar)) {
                int lastIndex = charIndexMap.get(currentChar);
                // 左邊界移動到重複字元的下一個位置
                // 注意：left 只能向右移動，不能向左退回
                left = Math.max(left, lastIndex + 1);
            }
            
            // 更新當前字元的最新索引
            charIndexMap.put(currentChar, right);
            
            // 計算當前視窗長度，並更新最大長度
            int currentLength = right - left + 1;
            maxLength = Math.max(maxLength, currentLength);
        }
        
        return maxLength;
    }
}