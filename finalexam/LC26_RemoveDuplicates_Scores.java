import java.util.*;

public class LC26_RemoveDuplicates_Scores {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n
        int n = sc.nextInt();
        int[] nums = new int[n];

        // 讀取序列（已排序）
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 執行去重
        int newLen = removeDuplicates(nums);

        // 輸出結果
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i]);
            if (i < newLen - 1) System.out.print(" ");
        }
        System.out.println();

        sc.close();
    }

    /**
     * 去除排序陣列中的重複項
     * @param nums 已排序的整數陣列
     * @return 壓縮後的新長度
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        // write 指向可寫位置，初始為 1（因為第一個一定保留）
        int write = 1;

        // i 從 1 開始逐一比較
        for (int i = 1; i < nums.length; i++) {
            // 如果與前一個保留的不同，寫入
            if (nums[i] != nums[write - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }

        return write;
    }
}
