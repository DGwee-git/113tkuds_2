import java.util.*;

public class M03_TopKConvenience {
    
    // 商品類別，用於存儲商品名稱、銷量和輸入順序
    static class Product {
        String name;
        int quantity;
        int order; // 輸入順序，用於穩定排序
        
        public Product(String name, int quantity, int order) {
            this.name = name;
            this.quantity = quantity;
            this.order = order;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("請輸入商品數量: ");
        // 讀取商品數量和 K 值
        int n = sc.nextInt();
        
        System.out.print("請輸入要查詢的 Top-K 值: ");
        int k = sc.nextInt();
        
        // 使用 Min-Heap 維護 Top-K
        // 優先順序：銷量小的優先（Min-Heap），銷量相同時輸入順序大的優先（後輸入的先被淘汰）
        PriorityQueue<Product> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.quantity != b.quantity) {
                return Integer.compare(a.quantity, b.quantity); // 銷量小的優先
            }
            return Integer.compare(b.order, a.order); // 輸入順序大的優先（後進先出）
        });
        
        System.out.println("請輸入 " + n + " 個商品資訊 (商品名稱 銷量):");
        // 處理每個商品
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            Product product = new Product(name, quantity, i);
            
            if (minHeap.size() < k) {
                // 堆還沒滿，直接加入
                minHeap.offer(product);
            } else {
                // 堆已滿，比較堆頂元素
                Product top = minHeap.peek();
                if (product.quantity > top.quantity || 
                   (product.quantity == top.quantity && product.order < top.order)) {
                    // 新商品銷量更高，或銷量相同但輸入更早，替換堆頂
                    minHeap.poll();
                    minHeap.offer(product);
                }
            }
        }
        
        // 將結果存入陣列並反向排序（從高到低）
        List<Product> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        
        // 按銷量從高到低排序，銷量相同時按輸入順序從小到大排序
        result.sort((a, b) -> {
            if (a.quantity != b.quantity) {
                return Integer.compare(b.quantity, a.quantity); // 銷量高的優先
            }
            return Integer.compare(a.order, b.order); // 輸入順序小的優先（先進先出）
        });
        
        // 輸出結果
        for (Product product : result) {
            System.out.println(product.name + " " + product.quantity);
        }
        
        sc.close();
    }
}

/*
 * Time Complexity: O(n log k + k log k)
 * 說明：Top-K 問題的時間複雜度分析
 * 1. 維護大小為 k 的 Min-Heap：每次插入/刪除操作需要 O(log k)，總共處理 n 個商品，所以是 O(n log k)
 * 2. 最終排序 k 個結果：O(k log k)
 * 3. 總時間複雜度：O(n log k + k log k)，當 k << n 時，這比完全排序的 O(n log n) 更高效
 * 
 * Space Complexity: O(k)
 * 說明：只需要維護大小為 k 的堆，空間複雜度為 O(k)
 * 
 * 穩定性說明：
 * - 銷量相同的商品按輸入順序排序（先輸入的排在前面）
 * - 這樣可以保證結果的一致性和可預測性
 */