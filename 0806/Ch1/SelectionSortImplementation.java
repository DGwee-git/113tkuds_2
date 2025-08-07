//練習 1.5：選擇排序實作

import java.util.Arrays;

public class SelectionSortImplementation {

    // 選擇排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("=== 選擇排序過程 ===");

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // 找出最小值
            for (int j = i + 1; j < n; j++) {
                compareCount++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 交換
            if (minIndex != i) {
                swap(arr, i, minIndex);
                swapCount++;
            }

            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("選擇排序完成: " + Arrays.toString(arr));
        System.out.println("比較次數: " + compareCount);
        System.out.println("交換次數: " + swapCount);
    }

    // 氣泡排序（用來比較效能）
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int compareCount = 0;
        int swapCount = 0;

        System.out.println("\n=== 氣泡排序過程 ===");

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                compareCount++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapCount++;
                }
            }
            System.out.println("第 " + (i + 1) + " 輪: " + Arrays.toString(arr));
        }

        System.out.println("氣泡排序完成: " + Arrays.toString(arr));
        System.out.println("比較次數: " + compareCount);
        System.out.println("交換次數: " + swapCount);
    }

    // 工具方法：交換
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] arr1 = {64, 25, 12, 22, 11};
        int[] arr2 = arr1.clone(); // 複製一份給氣泡排序

        selectionSort(arr1);
        bubbleSort(arr2);
    }
}
