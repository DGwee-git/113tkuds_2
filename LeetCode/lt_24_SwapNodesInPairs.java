public class lt_24_SwapNodesInPairs {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 兩兩交換鏈表節點
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = first.next;

            // 交換
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // 移動 prev
            prev = first;
        }

        return dummy.next;
    }

    // 輔助函式：建立鏈表
    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int val : arr) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 輔助函式：打印鏈表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    // 測試 main
    public static void main(String[] args) {
        ListNode head = createList(new int[]{1,2,3,4});
        System.out.print("Original list: ");
        printList(head);

        ListNode swapped = swapPairs(head);
        System.out.print("Swapped list: ");
        printList(swapped);
        // 預期: 2 -> 1 -> 4 -> 3
    }
}
