import java.util.Arrays;

public class lt_16_ThreeSumClosestLocal {
    public static int threeSumClosest(int[] nums, int target) {
        // 先排序，方便用雙指針法
        Arrays.sort(nums);
        int n = nums.length;
        // 預設結果為前三個數的和
        int closestSum = nums[0] + nums[1] + nums[2];

        // 固定一個數，另外用雙指針找另外兩個數
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果這次的 sum 更接近 target，更新結果
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }

                // 移動指針
                if (sum < target) {
                    left++; // 太小了 -> 左指針右移
                } else if (sum > target) {
                    right--; // 太大了 -> 右指針左移
                } else {
                    // 剛好等於 target，直接回傳
                    return sum;
                }
            }
        }
        return closestSum;
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Output: " + threeSumClosest(nums1, target1)); 
        // 預期輸出：2

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Output: " + threeSumClosest(nums2, target2)); 
        // 預期輸出：0
    }
}
