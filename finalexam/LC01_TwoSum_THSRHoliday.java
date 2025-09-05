import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        
        // 找出兩個班次的索引
        int[] result = twoSum(seats, target);
        
        // 輸出結果
        System.out.println(result[0] + " " + result[1]);
        
        scanner.close();
    }
    
    /**
     * 使用 HashMap 解決 Two Sum 問題
     * @param seats 各班次可釋出的座位數陣列
     * @param target 臨時新增需求總座位數
     * @return 兩個班次的索引，若無解則返回 [-1, -1]
     */
    public static int[] twoSum(int[] seats, int target) {
        // HashMap<需要的數, 索引>
        Map<Integer, Integer> needMap = new HashMap<>();
        
        // 一次遍歷
        for (int i = 0; i < seats.length; i++) {
            int currentSeats = seats[i];
            
            // 檢查之前是否有人在等這個數
            if (needMap.containsKey(currentSeats)) {
                // 找到解！返回之前記錄的索引和當前索引
                return new int[]{needMap.get(currentSeats), i};
            }
            
            // 記錄「還需要 target - currentSeats」
            int need = target - currentSeats;
            needMap.put(need, i);
        }
        
        // 無解
        return new int[]{-1, -1};
    }
}