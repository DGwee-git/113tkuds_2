import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        double[] nums1 = new double[n];
        double[] nums2 = new double[m];
        
        // 讀取第一個數列
        for (int i = 0; i < n; i++) {
            nums1[i] = scanner.nextDouble();
        }
        
        // 讀取第二個數列
        for (int i = 0; i < m; i++) {
            nums2[i] = scanner.nextDouble();
        }
        
        // 計算中位數
        double result = findMedianSortedArrays(nums1, nums2);
        
        // 輸出結果（保留1位小數）
        System.out.printf("%.1f%n", result);
        
        scanner.close();
    }
    
    /**
     * 使用二分搜尋找到兩個已排序陣列的中位數
     * @param nums1 第一個已排序陣列
     * @param nums2 第二個已排序陣列
     * @return 聯合集的中位數
     */
    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        // 確保 nums1 為較短的陣列（優化二分搜尋效率）
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int n = nums1.length;
        int m = nums2.length;
        int totalLength = n + m;
        int halfLength = (totalLength + 1) / 2; // 左半部分應有的元素個數
        
        // 在較短陣列進行二分搜尋
        int left = 0, right = n;
        
        while (left <= right) {
            // 在 nums1 中切割點為 i（左邊有 i 個元素）
            int i = (left + right) / 2;
            // 在 nums2 中切割點為 j（左邊有 j 個元素）
            int j = halfLength - i;
            
            // 處理邊界情況，使用極值
            double maxLeft1 = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double maxLeft2 = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double minRight1 = (i == n) ? Double.POSITIVE_INFINITY : nums1[i];
            double minRight2 = (j == m) ? Double.POSITIVE_INFINITY : nums2[j];
            
            // 檢查切割是否正確：左半部分的最大值 <= 右半部分的最小值
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 找到正確的切割點
                if (totalLength % 2 == 1) {
                    // 總長度為奇數，中位數是左半部分的最大值
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    // 總長度為偶數，中位數是中間兩個值的平均
                    double leftMax = Math.max(maxLeft1, maxLeft2);
                    double rightMin = Math.min(minRight1, minRight2);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                // nums1 的切割點太靠右，需要左移
                right = i - 1;
            } else {
                // nums1 的切割點太靠左，需要右移
                left = i + 1;
            }
        }
        
        // 理論上不會到達這裡（輸入保證有效）
        throw new IllegalArgumentException("輸入陣列不符合已排序條件");
    }
}