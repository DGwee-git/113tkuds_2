import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    
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
        int m = scanner.nextInt();
        
        // 構建第一個鏈結串列（院區1）
        ListNode list1 = buildList(scanner, n);
        
        // 構建第二個鏈結串列（院區2）
        ListNode list2 = buildList(scanner, m);
        
        // 合併兩個串列
        ListNode merged = mergeTwoLists(list1, list2);
        
        // 輸出結果
        printList(merged);
        
        scanner.close();
    }
    
    /**
     * 根據輸入構建鏈結串列
     * @param scanner 輸入掃描器
     * @param count 節點數量
     * @return 鏈結串列頭節點
     */
    private static ListNode buildList(Scanner scanner, int count) {
        if (count == 0) {
            return null;
        }
        
        ListNode head = null;
        ListNode tail = null;
        
        for (int i = 0; i < count; i++) {
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
        
        return head;
    }
    
    /**
     * 合併兩個已排序的鏈結串列
     * @param list1 第一個院區的掛號清單
     * @param list2 第二個院區的掛號清單
     * @return 合併後的排序清單
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 創建虛擬頭節點，簡化邊界處理
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy; // 維護合併串列的尾端
        
        // 雙指針比較合併
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                // list1 的節點較小，接到結果串列
                tail.next = list1;
                list1 = list1.next;
            } else {
                // list2 的節點較小，接到結果串列
                tail.next = list2;
                list2 = list2.next;
            }
            // 更新尾端指針
            tail = tail.next;
        }
        
        // 將剩餘未處理的節點直接接上
        // 由於兩個串列都已排序，剩餘部分可以直接連接
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        
        // 返回合併後的串列（跳過虛擬頭節點）
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
    
    /**
     * 遞歸版本的合併方法（替代解法）
     * @param list1 第一個串列
     * @param list2 第二個串列
     * @return 合併後的串列
     */
    public static ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // 基準案例
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // 遞歸案例：選擇較小的節點作為頭，遞歸合併剩餘部分
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
}