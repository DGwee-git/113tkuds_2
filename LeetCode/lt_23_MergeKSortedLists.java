import java.util.*;

public class lt_23_MergeKSortedLists {
    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    // 合併 k 個排序鏈表
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 將每個鏈表的頭節點加入優先隊列
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) pq.offer(node.next);
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
        ListNode l1 = createList(new int[]{1,4,5});
        ListNode l2 = createList(new int[]{1,3,4});
        ListNode l3 = createList(new int[]{2,6});

        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode merged = mergeKLists(lists);

        System.out.print("Merged list: ");
        printList(merged);
        // 預期: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    }
}
