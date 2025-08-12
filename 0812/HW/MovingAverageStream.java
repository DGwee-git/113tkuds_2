import java.util.*;

/**
 * 計算資料流中固定視窗大小的移動平均數，
 * 並支援中位數、最小值、最大值查詢
 */
public class MovingAverageStream {
    private int size;
    private Queue<Integer> window;
    private double sum;

    // 兩個 Heap 支援中位數
    private PriorityQueue<Integer> maxHeap; // 存小的一半（最大堆）
    private PriorityQueue<Integer> minHeap; // 存大的一半（最小堆）

    // 支援 min/max
    private PriorityQueue<Integer> minPQ; // 最小堆
    private PriorityQueue<Integer> maxPQ; // 最大堆（反轉比較器）

    // 延遲刪除（因為 heap 沒有直接刪除中間元素的方法）
    private Map<Integer, Integer> delayedRemoval;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;

        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minHeap = new PriorityQueue<>();

        this.minPQ = new PriorityQueue<>();
        this.maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        this.delayedRemoval = new HashMap<>();
    }

    // 加入新值並回傳平均數
    public double next(int val) {
        window.offer(val);
        sum += val;

        // 中位數結構更新
        addNumber(val);
        minPQ.offer(val);
        maxPQ.offer(val);

        // 超過視窗大小就移除舊值
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            removeNumber(removed);
            removeFromPQ(minPQ, removed);
            removeFromPQ(maxPQ, removed);
        }

        return sum / window.size();
    }

    public double getMedian() {
        balanceHeaps();
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return minPQ.peek();
    }

    public int getMax() {
        return maxPQ.peek();
    }

    // ===== 中位數相關 =====
    private void addNumber(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNumber(int num) {
        // 延遲刪除策略
        delayedRemoval.put(num, delayedRemoval.getOrDefault(num, 0) + 1);

        if (num <= maxHeap.peek()) {
            // 從 maxHeap 中刪除的數量將少一個
            if (num == maxHeap.peek()) {
                cleanHeap(maxHeap);
            }
        } else {
            if (num == minHeap.peek()) {
                cleanHeap(minHeap);
            }
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
            cleanHeap(maxHeap);
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
            cleanHeap(minHeap);
        }
    }

    private void cleanHeap(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayedRemoval.containsKey(heap.peek())) {
            int num = heap.peek();
            delayedRemoval.put(num, delayedRemoval.get(num) - 1);
            if (delayedRemoval.get(num) == 0) {
                delayedRemoval.remove(num);
            }
            heap.poll();
        }
    }

    // ===== Min/Max Heap 清理 =====
    private void removeFromPQ(PriorityQueue<Integer> pq, int val) {
        pq.remove(val); // 因為這裡是單純的 min/max，直接移除即可
    }

    // 測試
    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);
        System.out.println(ma.next(1));   // 1.0
        System.out.println(ma.next(10));  // 5.5
        System.out.println(ma.next(3));   // 4.666...
        System.out.println(ma.next(5));   // 6.0
        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}
