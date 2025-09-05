import java.util.*;

public class LC24_SwapPairs_Shifts {
    
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
        
        // 讀取輸入
        String line = scanner.nextLine().trim();
        
        // 構建鏈結串列
        ListNode head = buildList(line);
        
        // 兩兩交換節點
        ListNode result = swapPairs(head);
        
        // 輸出結果
        printList(result);
        
        scanner.close();
    }
    
    /**
     * 根據輸入字串構建鏈結串列
     * @param line 輸入字串
     * @return 鏈結串列頭節點
     */
    private static ListNode buildList(String line) {
        if (line.isEmpty()) {
            return null;
        }
        
        String[] tokens = line.split(" ");
        ListNode head = null;
        ListNode tail = null;
        
        for (String token : tokens) {
            if (token.trim().isEmpty()) continue;
            
            int val = Integer.parseInt(token.trim());
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
     * 兩兩交換相鄰節點
     * @param head 原始鏈結串列頭節點
     * @return 交換後的鏈結串列頭節點
     */
    public static ListNode swapPairs(ListNode head) {
        // 邊界案例：空串列或只有一個節點
        if (head == null || head.next == null) {
            return head;
        }
        
        // 創建虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy; // prev 始終指向當前要交換對的前一個節點
        
        // 當存在成對節點時持續交換
        while (prev.next != null && prev.next.next != null) {
            // 定義要交換的兩個節點
            ListNode first = prev.next;      // 第一個節點
            ListNode second = prev.next.next; // 第二個節點
            
            // 執行交換操作
            // 交換前：prev -> first -> second -> after
            // 交換後：prev -> second -> first -> after
            prev.next = second;           // prev 指向 second
            first.next = second.next;     // first 指向 after
            second.next = first;          // second 指向 first
            
            // 更新 prev 到下一對的前一個位置
            prev = first; // 此時 first 是交換後的第二個節點
        }
        
        return dummy.next;
    }
    
    /**
     * 遞歸版本的兩兩交換（替代解法）
     * @param head 鏈結串列頭節點
     * @return 交換後的頭節點
     */
    public static ListNode swapPairsRecursive(ListNode head) {
        // 基準案例：沒有節點或只有一個節點
        if (head == null || head.next == null) {
            return head;
        }
        
        // 保存第二個節點
        ListNode second = head.next;
        
        // 遞歸處理剩餘部分
        head.next = swapPairsRecursive(second.next);
        
        // 交換當前對
        second.next = head;
        
        // 返回新的頭節點（原來的第二個節點）
        return second;
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