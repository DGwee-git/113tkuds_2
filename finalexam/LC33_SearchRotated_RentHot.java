import java.util.*;

public class LC33_SearchRotated_RentHot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 target
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int ans = search(nums, target);
        System.out.println(ans);

        sc.close();
    }

    /**
     * 在旋轉陣列中搜尋 target
     * @param nums 被旋轉的升序陣列
     * @param target 要搜尋的值
     * @return target 的索引，若不存在則 -1
     */
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) return mid;

            // 判斷左半邊是否有序
            if (nums[l] <= nums[mid]) {
                // target 在左半範圍內
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            // 否則右半邊必定有序
            else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }
}
