import java.util.Arrays;

public class lt_26_RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // 慢指針
        for (int j = 1; j < nums.length; j++) { // 快指針
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println("Original array: " + Arrays.toString(nums));

        int newLength = removeDuplicates(nums);
        System.out.println("New length: " + newLength);

        System.out.print("Modified array: [");
        for (int k = 0; k < newLength; k++) {
            System.out.print(nums[k]);
            if (k != newLength - 1) System.out.print(", ");
        }
        System.out.println("]");
        // 預期: New length = 5, array = [0,1,2,3,4]
    }
}
