import java.util.Scanner;

public class M01_BuildHeap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入堆的類型 (max 或 min): ");
        String type = sc.next().trim(); // "max" or "min"
        
        System.out.print("請輸入陣列大小: ");
        int n = sc.nextInt();
        
        System.out.print("請輸入 " + n + " 個整數 (用空格分隔): ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // Bottom-up 建堆
        buildHeap(arr, type);

        // 輸出結果
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
        System.out.println();
    }

    // Bottom-up 建堆
    static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        // 從最後一個非葉節點開始 (n/2 - 1)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, n, i, type);
        }
    }

    // Heapify (自頂向下)
    static void heapifyDown(int[] arr, int n, int i, String type) {
        int extreme = i; // root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (type.equals("max")) {
            if (left < n && arr[left] > arr[extreme]) extreme = left;
            if (right < n && arr[right] > arr[extreme]) extreme = right;
        } else { // min
            if (left < n && arr[left] < arr[extreme]) extreme = left;
            if (right < n && arr[right] < arr[extreme]) extreme = right;
        }

        if (extreme != i) {
            swap(arr, i, extreme);
            heapifyDown(arr, n, extreme, type);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. Heapify 單次複雜度為 O(h)，h 為節點高度。
 * 2. 建堆時從底層往上，每層節點數 × 單點高度合計為 n/2 × 1 + n/4 × 2 + n/8 × 3 + ... = O(n)。
 * 3. 因此整體建堆時間複雜度為 O(n)，空間為 O(1)。
 */
