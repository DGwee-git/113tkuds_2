import java.util.*;

public class LC25_ReverseKGroup_Shifts {

    // 定義鏈結串列節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    // 主程式入口
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取 k
        int k = sc.nextInt();

        // 讀取序列，建立鏈結串列
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (sc.hasNextInt()) {
            tail.next = new ListNode(sc.nextInt());
            tail = tail.next;
        }

        ListNode head = dummy.next;

        // 進行 k 組反轉
        ListNode newHead = reverseKGroup(head, k);

        // 輸出結果
        printList(newHead);

        sc.close();
    }

    // 核心函式：反轉鏈結串列每 k 個節點
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;

        while (true) {
            // 找到當前區段的尾巴
            ListNode kth = getKth(prevGroupEnd, k);
            if (kth == null) break; // 不足 k，結束

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 反轉區段 [groupStart, kth]
            reverse(groupStart, kth);

            // 調整前後指標
            prevGroupEnd.next = kth;
            groupStart.next = nextGroupStart;

            // 移動 prevGroupEnd
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    // 取得從 node 開始往後數第 k 個節點
    private static ListNode getKth(ListNode node, int k) {
        while (node != null && k > 0) {
            node = node.next;
            k--;
        }
        return node;
    }

    // 反轉區段 [start, end]
    private static void reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode curr = start;
        ListNode stop = end.next;

        while (curr != stop) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    // 輸出鏈結串列
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" ");
            curr = curr.next;
        }
        System.out.println();
    }
}
