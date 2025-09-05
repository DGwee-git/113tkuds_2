import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int[] heights = new int[n];
        
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        
        // 計算最大輸出帶寬
        int result = maxArea(heights);
        
        // 輸出結果
        System.out.println(result);
        
        scanner.close();
    }
    
    /**
     * 使用雙指針方法找最大容器面積
     * @param height 油槽高度陣列
     * @return 最大輸出帶寬 (距離 × 最小高度)
     */
    public static int maxArea(int[] height) {
        // 邊界案例
        if (height == null || height.length < 2) {
            return 0;
        }
        
        int left = 0;                    // 左指針
        int right = height.length - 1;   // 右指針  
        int maxArea = 0;                 // 記錄最大面積
        
        // 雙指針向中間收縮
        while (left < right) {
            // 計算當前面積：寬度 × 較短的高度
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = width * minHeight;
            
            // 更新最大面積
            maxArea = Math.max(maxArea, currentArea);
            
            // 移動較短的邊（關鍵策略）
            if (height[left] < height[right]) {
                left++;   // 左邊較短，移動左指針
            } else {
                right--;  // 右邊較短或相等，移動右指針
            }
        }
        
        return maxArea;
    }
}