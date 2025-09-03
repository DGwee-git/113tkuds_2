import java.util.*;

public class lt_15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 避免重複

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++; right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        lt_15_ThreeSum solution = new lt_15_ThreeSum();
        int[] nums1 = {-1,0,1,2,-1,-4};
        int[] nums2 = {0,1,1};
        int[] nums3 = {0,0,0};
        System.out.println(solution.threeSum(nums1)); // 預期 [[-1,-1,2],[-1,0,1]]
        System.out.println(solution.threeSum(nums2)); // 預期 []
        System.out.println(solution.threeSum(nums3)); // 預期 [[0,0,0]]
    }
}

/*
解題思路：
- 先排序，固定一個數。
- 用雙指針找另外兩個數。
- 和 = 0 則存結果，並跳過重複元素。
- 和 < 0 則左指針右移；和 > 0 則右指針左移。
*/
