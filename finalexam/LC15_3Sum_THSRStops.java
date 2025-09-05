import java.util.*;

public class LC15_3Sum_THSRStops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int[] nums = new int[n];
        
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        // 找出所有三元組
        List<List<Integer>> result = threeSum(nums);
        
        // 輸出結果
        for (List<Integer> triplet : result) {
            System.out.println(triplet.get(0) + " " + triplet.get(1) + " " + triplet.get(2));
        }
        
        scanner.close();
    }
    
    /**
     * 使用排序 + 雙指針方法找所有和為0的三元組
     * @param nums 站點調整量陣列
     * @return 所有不重複的三元組列表
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 邊界案例：少於3個元素
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // 排序陣列，便於去重和雙指針操作
        Arrays.sort(nums);
        
        // 固定第一個元素
        for (int i = 0; i < nums.length - 2; i++) {
            // 優化：如果當前數字大於0，後面的數字都大於0，不可能和為0
            if (nums[i] > 0) {
                break;
            }
            
            // 跳過重複的第一個元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 使用雙指針找剩餘兩個數
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i]; // 目標：nums[left] + nums[right] = -nums[i]
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    // 找到一組解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳過重複的左指針值
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    
                    // 跳過重複的右指針值
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移動兩個指針
                    left++;
                    right--;
                    
                } else if (sum < target) {
                    // 和太小，移動左指針增大和
                    left++;
                } else {
                    // 和太大，移動右指針減小和
                    right--;
                }
            }
        }
        
        return result;
    }
}