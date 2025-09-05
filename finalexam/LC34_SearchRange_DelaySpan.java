import java.util.*;

public class LC34_SearchRange_DelaySpan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] ans = searchRange(nums, target);
        System.out.println(ans[0] + " " + ans[1]);

        sc.close();
    }

    /**
     * 找出 target 在排序陣列中的首末位置
     * @param nums 已排序陣列
     * @param target 目標值
     * @return 長度為 2 的陣列 [first, last]，若不存在則 [-1, -1]
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        // 檢查 target 是否存在
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = lowerBound(nums, target + 1) - 1;
        return new int[]{left, right};
    }

    /**
     * lower_bound: 找到第一個 >= target 的索引
     */
    private static int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
