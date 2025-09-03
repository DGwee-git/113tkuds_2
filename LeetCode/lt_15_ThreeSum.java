import java.util.*;

public class lt_15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // 排序，方便雙指針處理

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 避免重複

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的數字
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 需要更大
                } else {
                    right--; // 需要更小
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        lt_15_ThreeSum solution = new lt_15_ThreeSum();

        int[][] testCases = {
            {-1, 0, 1, 2, -1, -4},
            {0, 1, 1},
            {0, 0, 0}
        };

        for (int[] test : testCases) {
            System.out.println("Input: " + Arrays.toString(test));
            List<List<Integer>> output = solution.threeSum(test);
            System.out.println("Output: " + output);
            System.out.println();
        }
    }
}
