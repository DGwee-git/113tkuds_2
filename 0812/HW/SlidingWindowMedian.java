import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> maxHeap; // 小的一半（最大堆）
    private PriorityQueue<Integer> minHeap; // 大的一半（最小堆）
    private Map<Integer, Integer> delayed;  // 延遲刪除的計數
    private int leftSize, rightSize;        // 有效元素數量（不含延遲刪除）

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();
        leftSize = 0;
        rightSize = 0;
    }

    // 主函數
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]); // 新元素進 heap

            if (i >= k) { // 移除視窗外的元素
                removeNum(nums[i - k]);
            }

            if (i >= k - 1) { // 開始記錄中位數
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
            leftSize++;
        } else {
            minHeap.offer(num);
            rightSize++;
        }
        rebalance();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= maxHeap.peek()) {
            leftSize--;
            if (num == maxHeap.peek()) prune(maxHeap);
        } else {
            rightSize--;
            if (!minHeap.isEmpty() && num == minHeap.peek()) prune(minHeap);
        }
        rebalance();
    }

    private void rebalance() {
        if (leftSize > rightSize + 1) { // 左邊多了
            minHeap.offer(maxHeap.poll());
            leftSize--;
            rightSize++;
            prune(maxHeap);
        } else if (rightSize > leftSize) { // 右邊多了
            maxHeap.offer(minHeap.poll());
            rightSize--;
            leftSize++;
            prune(minHeap);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) delayed.remove(num);
                heap.poll();
            } else break;
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) {
            return maxHeap.peek();
        } else {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        }
    }

    // 測試
    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums1, 3)));
        // 預期: [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums2, 2)));
        // 預期: [1.5, 2.5, 3.5]
    }
}
