import java.util.*;

public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int target = scanner.nextInt();
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        
        // 找出所有四元組
        List<List<Integer>> result = fourSum(nums, target);
        
        // 輸出結果
        for (List<Integer> quadruplet : result) {
            System.out.println(quadruplet.get(0) + " " + quadruplet.get(1) + 
                             " " + quadruplet.get(2) + " " + quadruplet.get(3));
        }
        
        scanner.close();
    }
    
    /**
     * 使用排序 + 雙層枚舉 + 雙指針方法找所有和為target的四元組
     * @param nums 物資價格陣列
     * @param target 目標預算
     * @return 所有不重複的四元組列表
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 邊界案例：少於4個元素
        if (nums == null || nums.length < 4) {
            return result;
        }
        
        // 排序陣列，便於去重和雙指針操作
        Arrays.sort(nums);
        
        // 雙層枚舉前兩個元素
        for (int i = 0; i < nums.length - 3; i++) {
            // 跳過重複的第一個元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // 剪枝優化：最小四個數的和都大於target
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            
            // 剪枝優化：當前數與最大三個數的和都小於target
            if ((long)nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 跳過重複的第二個元素
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                // 剪枝優化：當前兩個數與最小兩個數的和都大於target
                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                
                // 剪枝優化：當前兩個數與最大兩個數的和都小於target
                if ((long)nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }
                
                // 使用雙指針找剩餘兩個數
                int left = j + 1;
                int right = nums.length - 1;
                
                while (left < right) {
                    // 注意：使用long避免整數溢出
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        // 找到一組解
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
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
        }
        
        return result;
    }
}