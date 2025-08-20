package midterm;
import java.util.Scanner;

public class M01_BuildHeap {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 讀取堆的類型 (max 或 min)
        String type = sc.next();
        
        // 讀取元素個數
        int n = sc.nextInt();
        
        // 讀取元素
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        // 建堆
        buildHeap(arr, type);
        
        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();
        
        sc.close();
    }
    
    /**
     * 自底向上建堆 (Bottom-up Build Heap)
     * @param arr 要建堆的陣列
     * @param type "max" 表示最大堆，"min" 表示最小堆
     */
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        boolean isMaxHeap = type.equals("max");
        
        // 從最後一個非葉節點開始向上進行 heapifyDown
        // 最後一個非葉節點的索引是 (n/2)-1
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, isMaxHeap);
        }
    }
    
    /**
     * 向下調整堆 (Heapify Down)
     * @param arr 堆陣列
     * @param heapSize 堆的大小
     * @param i 當前需要調整的節點索引
     * @param isMaxHeap true 表示最大堆，false 表示最小堆
     */
    public static void heapifyDown(int[] arr, int heapSize, int i, boolean isMaxHeap) {
        int target = i;           // 假設當前節點就是目標位置
        int leftChild = 2 * i + 1;   // 左子節點索引
        int rightChild = 2 * i + 2;  // 右子節點索引
        
        // 檢查左子節點
        if (leftChild < heapSize) {
            if (isMaxHeap) {
                // 最大堆：找最大值
                if (arr[leftChild] > arr[target]) {
                    target = leftChild;
                }
            } else {
                // 最小堆：找最小值
                if (arr[leftChild] < arr[target]) {
                    target = leftChild;
                }
            }
        }
        
        // 檢查右子節點
        if (rightChild < heapSize) {
            if (isMaxHeap) {
                // 最大堆：找最大值
                if (arr[rightChild] > arr[target]) {
                    target = rightChild;
                }
            } else {
                // 最小堆：找最小值
                if (arr[rightChild] < arr[target]) {
                    target = rightChild;
                }
            }
        }
        
        // 如果目標位置不是當前節點，則交換並遞歸調整
        if (target != i) {
            swap(arr, i, target);
            heapifyDown(arr, heapSize, target, isMaxHeap);
        }
    }
    
    /**
     * 交換陣列中兩個元素
     * @param arr 陣列
     * @param i 第一個元素的索引
     * @param j 第二個元素的索引
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆的時間複雜度分析
 * 1. 葉節點（約 n/2 個）不需要調整，高度為 0
 * 2. 倒數第二層節點最多需要下沉 1 層，倒數第三層最多下沉 2 層，以此類推
 * 3. 總的比較次數上界為 Σ(i=0 to log n) (n/2^(i+1)) * i ≤ n * Σ(i=1 to ∞) i/2^i = n * 2 = O(n)
 */