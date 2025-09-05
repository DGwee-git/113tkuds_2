import java.util.*;

public class LC23_MergeKLists_Hospitals {
    
    /**
     * 定義鏈結串列節點
     */
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode() {}
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取院區數量
        int k = scanner.nextInt();
        scanner.nextLine(); // 消耗換行符
        
        // 構建k個鏈結串列
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = buildList(scanner);
        }
        
        // 合併所有串列
        ListNode merged = mergeKLists(lists);
        
        // 輸出結果
        printList(merged);
        
        scanner.close();
    }
    
    /**
     * 根據輸入構建鏈結串列（以-1結尾）
     * @param scanner 輸入掃描器
     * @return 鏈結串列頭節點
     */
    private static ListNode buildList(Scanner scanner) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            return null;
        }
        
        String[] tokens = line.split(" ");
        ListNode head = null;
        ListNode tail = null;
        
        for (String token : tokens) {
            int val = Integer.parseInt(token);
            if (val == -1) {
                break; // 遇到-1結束
            }
            
            ListNode newNode = new ListNode(val);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        return head;
    }
    
    /**
     * 使用最小堆合併k個已排序串列
     * @param lists k個已排序的鏈結串列數組
     * @return 合併後的排序串列
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // 建立最小堆，按節點值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // 初始化：將所有非空串列的頭節點加入堆
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        // 創建虛擬頭節點
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        // 持續從堆中取出最小節點
        while (!minHeap.isEmpty()) {
            // 取出當前最小的節點
            ListNode minNode = minHeap.poll();
            
            // 將該節點接到結果串列
            tail.next = minNode;
            tail = tail.next;
            
            // 如果該節點還有下一個節點，將下一個節點加入堆
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        
        return dummy.next;
    }
    
    /**
     * 分治法合併k個串列（替代解法）
     * @param lists k個已排序的鏈結串列數組
     * @return 合併後的排序串列
     */
    public static ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        return mergeHelper(lists, 0, lists.length - 1);
    }
    
    /**
     * 分治法輔助函數
     * @param lists 串列數組
     * @param start 開始索引
     * @param end 結束索引
     * @return 合併後的串列
     */
    private static ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        
        if (start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        
        return mergeTwoLists(left, right);
    }
    
    /**
     * 合併兩個已排序串列（輔助函數）
     * @param list1 第一個串列
     * @param list2 第二個串列
     * @return 合併後的串列
     */
    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        
        tail.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }
    
    /**
     * 輸出鏈結串列
     * @param head 頭節點
     */
    private static void printList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        
        // 輸出結果
        if (result.isEmpty()) {
            System.out.println(); // 空串列
        } else {
            for (int i = 0; i < result.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(result.get(i));
            }
            System.out.println();
        }
    }
}