public class lt_25_ReverseNodesInKGroup {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // K 個一組翻轉鏈表
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevGroup = dummy;
        ListNode end = dummy;

        while (true) {
            // 找到本組的結尾節點
            int count = 0;
            while (count < k && end != null) {
                end = end.next;
                count++;
            }
            if (end == null) break;

            ListNode start = prevGroup.next;
            ListNode nextGroup = end.next;
            end.next = null; // 暫時斷開鏈表

            // 反轉本組
            prevGroup.next = reverseList(start);
            start.next = nextGroup;

            // 移動 prevGroup 和 end 到下一組
            prevGroup = start;
            end = prevGroup;
        }

        return dummy.next;
    }

    // 反轉鏈表
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTmp;
        }
        return prev;
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
        ListNode head = createList(new int[]{1,2,3,4,5});
        int k = 2;
        System.out.print("Original list: ");
        printList(head);

        ListNode reversed = reverseKGroup(head, k);
        System.out.print("Reversed in k=" + k + ": ");
        printList(reversed);
        // 預期: 2 -> 1 -> 4 -> 3 -> 5
    }
}
