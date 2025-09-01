import java.util.HashMap;
import java.util.Map;

public class lt_01_twosum {
    public static int[] twoSum(int[] nums, int target) {
        // 建立 HashMap：key = 陣列中的值, value = 該值的索引
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 找到補數
            
            // 如果補數已經存在於 map，表示找到答案
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // 把當前數字放入 map (供之後的數字使用)
            map.put(nums[i], i);
        }
        
        // 題目保證一定有答案，所以這裡不會被執行
        throw new IllegalArgumentException("No solution");
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("答案: [" + result1[0] + "," + result1[1] + "]");

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("答案: [" + result2[0] + "," + result2[1] + "]");

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = twoSum(nums3, target3);
        System.out.println("答案: [" + result3[0] + "," + result3[1] + "]");
    }
}
