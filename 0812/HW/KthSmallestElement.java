import java.util.*;

public class KthSmallestElement {

    // 方法 1：大小為 K 的 Max Heap
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 保持大小為 k
            }
        }
        return maxHeap.peek();
    }

    // 方法 2：Min Heap，取出 K 次
    public static int kthSmallest_MinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        for (int i = 1; i < k; i++) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    // 測試 & 效能比較
    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];
            System.out.println("陣列: " + Arrays.toString(arr) + ", K = " + k);

            long start1 = System.nanoTime();
            int ans1 = kthSmallest_MaxHeap(arr, k);
            long time1 = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            int ans2 = kthSmallest_MinHeap(arr, k);
            long time2 = System.nanoTime() - start2;

            System.out.println("方法 1 (Max Heap) → 答案: " + ans1 + ", 耗時: " + time1 + " ns");
            System.out.println("方法 2 (Min Heap) → 答案: " + ans2 + ", 耗時: " + time2 + " ns");
            System.out.println();
        }
    }
}
