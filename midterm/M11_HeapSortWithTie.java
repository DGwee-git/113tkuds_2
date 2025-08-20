import java.util.*;

// 封裝學生成績和原始索引的類別
class Student {
    int score;
    int index;
    
    Student(int score, int index) {
        this.score = score;
        this.index = index;
    }
}

public class M11_HeapSortWithTie {
    
    // Max-Heap 實現
    static class MaxHeap {
        private List<Student> heap;
        
        public MaxHeap() {
            heap = new ArrayList<>();
        }
        
        public void insert(Student student) {
            heap.add(student);
            heapifyUp(heap.size() - 1);
        }
        
        public Student extractMax() {
            if (heap.isEmpty()) {
                return null;
            }
            
            Student max = heap.get(0);
            Student last = heap.get(heap.size() - 1);
            heap.set(0, last);
            heap.remove(heap.size() - 1);
            
            if (!heap.isEmpty()) {
                heapifyDown(0);
            }
            
            return max;
        }
        
        public boolean isEmpty() {
            return heap.isEmpty();
        }
        
        public int size() {
            return heap.size();
        }
        
        // 向上調整（用於插入）
        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                
                if (compare(heap.get(index), heap.get(parentIndex)) <= 0) {
                    break;
                }
                
                swap(index, parentIndex);
                index = parentIndex;
            }
        }
        
        // 向下調整（用於刪除）
        private void heapifyDown(int index) {
            int size = heap.size();
            
            while (true) {
                int largest = index;
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;
                
                if (leftChild < size && compare(heap.get(leftChild), heap.get(largest)) > 0) {
                    largest = leftChild;
                }
                
                if (rightChild < size && compare(heap.get(rightChild), heap.get(largest)) > 0) {
                    largest = rightChild;
                }
                
                if (largest == index) {
                    break;
                }
                
                swap(index, largest);
                index = largest;
            }
        }
        
        // 比較函數：分數高者優先，分數相同時索引小者優先
        private int compare(Student s1, Student s2) {
            if (s1.score != s2.score) {
                return Integer.compare(s1.score, s2.score);
            }
            // 分數相同時，索引小的優先（在Max-Heap中為較大）
            return Integer.compare(s2.index, s1.index);
        }
        
        private void swap(int i, int j) {
            Student temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
    
    // 使用Max-Heap進行堆排序
    public static int[] heapSort(int[] scores) {
        MaxHeap maxHeap = new MaxHeap();
        
        // 將所有學生加入Max-Heap
        for (int i = 0; i < scores.length; i++) {
            maxHeap.insert(new Student(scores[i], i));
        }
        
        // 從Max-Heap依序取出，得到遞減序列
        int[] result = new int[scores.length];
        for (int i = scores.length - 1; i >= 0; i--) {
            Student student = maxHeap.extractMax();
            result[i] = student.score;
        }
        
        return result;
    }
    
    // 替代實現：使用Min-Heap（Java內建PriorityQueue）
    public static int[] heapSortWithPriorityQueue(int[] scores) {
        // 建立Min-Heap，優先度：分數小者優先，分數相同時索引小者優先
        PriorityQueue<Student> minHeap = new PriorityQueue<>((s1, s2) -> {
            if (s1.score != s2.score) {
                return Integer.compare(s1.score, s2.score);
            }
            return Integer.compare(s1.index, s2.index);
        });
        
        // 將所有學生加入Min-Heap
        for (int i = 0; i < scores.length; i++) {
            minHeap.offer(new Student(scores[i], i));
        }
        
        // 從Min-Heap依序取出，得到遞增序列
        int[] result = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            Student student = minHeap.poll();
            result[i] = student.score;
        }
        
        return result;
    }
    
    // 輔助方法：將陣列轉換為字串輸出
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        return sb.toString();
    }
    
    // 驗證排序結果的輔助方法
    public static boolean verifySortedWithTieRule(int[] original, int[] sorted) {
        if (original.length != sorted.length) {
            return false;
        }
        
        // 建立原始索引映射
        Map<Integer, List<Integer>> scoreToIndices = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            scoreToIndices.computeIfAbsent(original[i], k -> new ArrayList<>()).add(i);
        }
        
        // 檢查排序結果
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i] > sorted[i + 1]) {
                return false; // 不是遞增序列
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int[] scores = new int[n];
        
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }
        
        // 執行堆排序
        int[] sortedScores = heapSort(scores);
        
        // 輸出結果
        System.out.println(arrayToString(sortedScores));
        
        scanner.close();
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：建立堆需要執行 n 次插入操作，每次插入的時間複雜度為 O(log n)
 *       從堆中取出所有元素需要執行 n 次刪除操作，每次刪除的時間複雜度為 O(log n)
 *       總時間複雜度為 O(n log n) + O(n log n) = O(n log n)
 *       空間複雜度為 O(n)，用於存儲堆和結果陣列
 */