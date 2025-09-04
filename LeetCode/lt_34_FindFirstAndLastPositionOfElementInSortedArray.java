import java.util.Arrays;

public class lt_34_FindFirstAndLastPositionOfElementInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        res[0] = findBound(nums, target, true);
        res[1] = findBound(nums, target, false);
        return res;
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) right = mid - 1;
                else left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums1, 8))); // [3,4]

        int[] nums2 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums2, 6))); // [-1,-1]

        int[] nums3 = {};
        System.out.println(Arrays.toString(searchRange(nums3, 0))); // [-1,-1]
    }
}
