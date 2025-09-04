import java.util.Arrays;

public class lt_31_NextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 找到第一個下降元素
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) swap(nums, start++, end--);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // [1,3,2]

        int[] nums2 = {3,2,1};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // [1,2,3]

        int[] nums3 = {1,1,5};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // [1,5,1]
    }
}
