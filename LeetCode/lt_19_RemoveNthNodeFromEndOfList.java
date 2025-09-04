public class lt_19_RemoveNthNodeFromEndOfList {
    // 定義 ListNode
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // 移除倒數第 n 個節點
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // 虛擬頭節點
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n+1 步，保持距離
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 一起走，直到 fast 到尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪掉 slow.next
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 輔助：建立鏈表
    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int num : arr) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 輔助：輸出鏈表
    private static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) System.out.print(" -> ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 測試 main
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createList(arr);

        System.out.print("原始鏈表: ");
        printList(head);

        head = removeNthFromEnd(head, 2);
        System.out.print("移除倒數第 2 個節點後: ");
        printList(head);
        // 預期輸出: 1 -> 2 -> 3 -> 5
    }
}
