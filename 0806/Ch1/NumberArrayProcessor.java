//練習 1.3：數字陣列處理器

import java.util.*;

public class NumberArrayProcessor {

    // 1. 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    // 2. 合併兩個已排序陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) merged[k++] = arr1[i++];
        while (j < arr2.length) merged[k++] = arr2[j++];

        return merged;
    }

    // 3. 找出出現頻率最高的元素
    public static int findMostFrequentElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mostFrequent = arr[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    // 4. 陣列分割成兩個相等（或近似相等）的子陣列
    public static int[][] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] firstHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] secondHalf = Arrays.copyOfRange(arr, mid, arr.length);
        return new int[][]{firstHalf, secondHalf};
    }

    // 工具方法：列印陣列
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 測試主程式
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 4, 3, 2, 6, 6, 1, 2};

        // 1. 移除重複
        System.out.println("原始陣列:");
        printArray(arr);
        System.out.println("移除重複元素後:");
        printArray(removeDuplicates(arr));

        // 2. 合併兩個已排序陣列
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};
        System.out.println("\n合併兩個已排序陣列:");
        printArray(mergeSortedArrays(sorted1, sorted2));

        // 3. 找出最常出現的元素
        System.out.println("\n最常出現的元素: " + findMostFrequentElement(arr));

        // 4. 分割陣列
        System.out.println("\n分割陣列:");
        int[][] split = splitArray(arr);
        System.out.print("第一部分: ");
        printArray(split[0]);
        System.out.print("第二部分: ");
        printArray(split[1]);
    }
}

