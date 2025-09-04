public class lt_21_MergeTwoSortedLists {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 合併兩個排序好的鏈結串列
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // 假頭節點，方便操作
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 把剩下的直接接上
        if (list1 != null) current.next = list1;
        if (list2 != null) current.next = list2;

        return dummy.next;
    }

    // 輔助函式：輸出鏈表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // 測試
    public static void main(String[] args) {
        // 建立第一個鏈表: 1 -> 2 -> 4
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // 建立第二個鏈表: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        System.out.print("List1: ");
        printList(l1);
        System.out.print("List2: ");
        printList(l2);

        ListNode merged = mergeTwoLists(l1, l2);
        System.out.print("Merged: ");
        printList(merged);
    }
}
