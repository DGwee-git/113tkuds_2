import java.util.Arrays;

public class lt_27_RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int i = 0; // 慢指針
        for (int j = 0; j < nums.length; j++) { // 快指針
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,2,3,4,2,5};
        int val = 2;
        System.out.println("Original array: " + Arrays.toString(nums));

        int newLength = removeElement(nums, val);
        System.out.println("New length: " + newLength);

        System.out.print("Modified array: [");
        for (int k = 0; k < newLength; k++) {
            System.out.print(nums[k]);
            if (k != newLength - 1) System.out.print(", ");
        }
        System.out.println("]");
        // 預期: New length = 4, array = [3,3,4,5]
    }
}
