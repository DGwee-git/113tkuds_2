import java.util.Scanner;

public class M02_YouBikeNextArrival {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 讀取補給時間數量
        int n = sc.nextInt();
        sc.nextLine(); // 消費換行符
        
        // 讀取補給時間並轉換為分鐘數
        int[] supplyTimes = new int[n];
        for (int i = 0; i < n; i++) {
            String timeStr = sc.nextLine();
            supplyTimes[i] = timeToMinutes(timeStr);
        }
        
        // 讀取查詢時間
        String queryStr = sc.nextLine();
        int queryTime = timeToMinutes(queryStr);
        
        // 二分搜尋找到第一個大於查詢時間的補給時間
        int nextIndex = binarySearchFirstGreater(supplyTimes, queryTime);
        
        // 輸出結果
        if (nextIndex == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(minutesToTime(supplyTimes[nextIndex]));
        }
        
        sc.close();
    }
    
    /**
     * 將時間字串 (HH:mm) 轉換為自 00:00 起的分鐘數
     * @param timeStr 時間字串，格式為 "HH:mm"
     * @return 分鐘數
     */
    public static int timeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    /**
     * 將分鐘數轉換為時間字串 (HH:mm)
     * @param minutes 自 00:00 起的分鐘數
     * @return 時間字串，格式為 "HH:mm"
     */
    public static String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
    
    /**
     * 二分搜尋找到第一個大於目標值的元素索引
     * @param arr 已排序的陣列
     * @param target 目標值
     * @return 第一個大於 target 的元素索引，如果不存在則返回 -1
     */
    public static int binarySearchFirstGreater(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] > target) {
                // 當前元素大於目標值，記錄結果並繼續在左半部分搜尋
                result = mid;
                right = mid - 1;
            } else {
                // 當前元素小於等於目標值，在右半部分搜尋
                left = mid + 1;
            }
        }
        
        return result;
    }
}

/*
 * Time Complexity: O(log n)
 * 說明：二分搜尋的時間複雜度分析
 * 1. 時間轉換操作為 O(1)，總共執行 n+1 次，時間複雜度為 O(n)
 * 2. 二分搜尋在已排序陣列中查找，每次比較後搜尋範圍縮小一半，時間複雜度為 O(log n)
 * 3. 總體時間複雜度為 O(n + log n) = O(n)，但搜尋部分是 O(log n)
 */