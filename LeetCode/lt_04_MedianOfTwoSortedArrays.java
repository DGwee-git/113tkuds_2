//package LeetCode;

public class lt_04_MedianOfTwoSortedArrays{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 長度 <= nums2
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半邊元素個數

        int left = 0, right = m;
        while (left <= right) {
            int i = (left + right) / 2; // nums1 的分割點
            int j = totalLeft - i;      // nums2 的分割點

            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正確分割
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (Math.max(nums1LeftMax, nums2LeftMax) + 
                            Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1; // i 太大，往左縮小
            } else {
                left = i + 1; // i 太小，往右擴大
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    public static void main(String[] args) {
        lt_04_MedianOfTwoSortedArrays solution = new lt_04_MedianOfTwoSortedArrays();

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Example 1:");
        System.out.println("Input: nums1 = [1,3], nums2 = [2]");
        System.out.println("Output: " + solution.findMedianSortedArrays(nums1, nums2));
        System.out.println("Explanation: merged array = [1,2,3] and median is 2.\n");

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Example 2:");
        System.out.println("Input: nums1 = [1,2], nums2 = [3,4]");
        System.out.println("Output: " + solution.findMedianSortedArrays(nums3, nums4));
        System.out.println("Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.\n");
    }
}
