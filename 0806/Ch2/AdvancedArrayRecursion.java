//練習 2.2：陣列遞迴操作進階

import java.util.Arrays;

public class AdvancedArrayRecursion {

    // 1. 遞迴快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    // 2. 遞迴合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        return mergeHelper(arr1, arr2, 0, 0, new int[arr1.length + arr2.length], 0);
    }

    private static int[] mergeHelper(int[] arr1, int[] arr2, int i, int j, int[] result, int k) {
        if (i == arr1.length && j == arr2.length) return result;

        if (i == arr1.length) {
            result[k] = arr2[j];
            return mergeHelper(arr1, arr2, i, j + 1, result, k + 1);
        }
        if (j == arr2.length) {
            result[k] = arr1[i];
            return mergeHelper(arr1, arr2, i + 1, j, result, k + 1);
        }

        if (arr1[i] <= arr2[j]) {
            result[k] = arr1[i];
            return mergeHelper(arr1, arr2, i + 1, j, result, k + 1);
        } else {
            result[k] = arr2[j];
            return mergeHelper(arr1, arr2, i, j + 1, result, k + 1);
        }
    }

    // 3. 遞迴尋找第 k 小元素 (QuickSelect)
    public static int kthSmallest(int[] arr, int left, int right, int k) {
        if (left <= right) {
            int pivotIndex = partition(arr, left, right);
            int count = pivotIndex - left + 1; // pivot 是第幾小

            if (count == k) return arr[pivotIndex];
            if (k < count) return kthSmallest(arr, left, pivotIndex - 1, k);
            return kthSmallest(arr, pivotIndex + 1, right, k - count);
        }
        return Integer.MIN_VALUE; // 找不到
    }

    // 4. 遞迴檢查是否存在子序列總和等於目標值
    public static boolean subsetSumExists(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index >= arr.length) return false;

        // 選擇當前元素
        if (arr[index] <= target && subsetSumExists(arr, target - arr[index], index + 1))
            return true;

        // 不選擇當前元素
        return subsetSumExists(arr, target, index + 1);
    }

    // 工具：交換
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    // 測試主程式
    public static void main(String[] args) {
        // 測試快速排序
        int[] arr1 = {5, 2, 9, 1, 5, 6};
        System.out.println("原始陣列: " + Arrays.toString(arr1));
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("快速排序後: " + Arrays.toString(arr1));

        // 測試合併已排序陣列
        int[] sorted1 = {1, 3, 5};
        int[] sorted2 = {2, 4, 6};
        System.out.println("合併後: " + Arrays.toString(mergeSortedArrays(sorted1, sorted2)));

        // 測試第 k 小元素
        int[] arr2 = {7, 10, 4, 3, 20, 15};
        int k = 3;
        System.out.println("第 " + k + " 小元素: " + kthSmallest(arr2.clone(), 0, arr2.length - 1, k));

        // 測試子序列總和
        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子序列總和 = " + target + ": " + subsetSumExists(arr3, target, 0));
    }
}
