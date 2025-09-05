import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    
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
        int n = scanner.nextInt();
        
        // 構建鏈結串列
        ListNode head = null;
        ListNode tail = null;
        
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            ListNode newNode = new ListNode(val);
            
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        
        int k = scanner.nextInt();
        
        // 刪除倒數第k個節點
        ListNode result = removeNthFromEnd(head, k);
        
        // 輸出結果
        printList(result);
        
        scanner.close();
    }
    
    /**
     * 使用雙指針方法刪除倒數第n個節點
     * @param head 鏈結串列頭節點
     * @param n 倒數第n個位置
     * @return 刪除節點後的新頭節點
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 創建虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;  // 快指針
        ListNode slow = dummy;  // 慢指針
        
        // Step 1: 快指針先走 n+1 步
        // 多走一步是為了讓慢指針停在待刪除節點的前一個位置
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Step 2: 快慢指針同時移動，直到快指針到達末尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Step 3: 此時慢指針指向待刪除節點的前一個節點
        // 執行刪除操作
        slow.next = slow.next.next;
        
        // 返回新的頭節點（可能原頭節點被刪除了）
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