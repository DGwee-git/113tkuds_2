import java.util.Arrays;

public class lt_35_SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,6};
        System.out.println(Arrays.toString(nums1) + ", target=5 -> " + searchInsert(nums1, 5)); // 2
        System.out.println(Arrays.toString(nums1) + ", target=2 -> " + searchInsert(nums1, 2)); // 1
        System.out.println(Arrays.toString(nums1) + ", target=7 -> " + searchInsert(nums1, 7)); // 4
        System.out.println(Arrays.toString(nums1) + ", target=0 -> " + searchInsert(nums1, 0)); // 0
    }
}
