import java.util.*;

public class LC27_RemoveElement_Recycle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 n 與 val
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];

        // 讀取清單
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 執行移除 val
        int newLen = removeElement(nums, val);

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
     * 從陣列中移除所有等於 val 的元素（就地覆寫）
     * @param nums 整數陣列
     * @param val  要移除的值
     * @return 移除後的新長度
     */
    public static int removeElement(int[] nums, int val) {
        int write = 0; // 指向下一個可寫位置
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x; // 保留非 val 元素
            }
        }
        return write;
    }
}
