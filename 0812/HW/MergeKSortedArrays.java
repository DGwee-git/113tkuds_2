import java.util.*;

public class MergeKSortedArrays {

    // 包裝每個 heap 元素的資訊
    static class HeapNode {
        int value;       // 元素值
        int arrayIndex;  // 來自第幾個陣列
        int elementIndex; // 該陣列中的索引

        HeapNode(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        // 初始化：把每個陣列的第一個元素放進 heap
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new HeapNode(arrays[i][0], i, 0));
            }
        }

        // 合併過程
        while (!minHeap.isEmpty()) {
            HeapNode node = minHeap.poll();
            result.add(node.value);

            int nextIndex = node.elementIndex + 1;
            if (nextIndex < arrays[node.arrayIndex].length) {
                minHeap.offer(new HeapNode(arrays[node.arrayIndex][nextIndex], node.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    // 測試
    public static void main(String[] args) {
        int[][] case1 = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
        int[][] case2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] case3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(case1)); // [1,1,2,3,4,4,5,6]
        System.out.println(mergeKSortedArrays(case2)); // [1,2,3,4,5,6,7,8,9]
        System.out.println(mergeKSortedArrays(case3)); // [0,1]
    }
}
