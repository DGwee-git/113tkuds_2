// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class lt_02_addtwonumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立虛擬頭節點
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0; // 進位

        // 直到兩個鏈表都走完
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;

            carry = sum / 10; // 計算進位
            curr.next = new ListNode(sum % 10); // 建立新節點
            curr = curr.next;

            // 移動到下一個節點
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 如果最後還有進位
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return dummyHead.next; // 回傳真正的頭節點
    }

    // 測試程式
    public static void main(String[] args) {
        // 建立 l1 = [2,4,3]
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        // 建立 l2 = [5,6,4]
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        lt_02_addtwonumbers solution = new lt_02_addtwonumbers();
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 印出結果
        System.out.print("答案: [");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(",");
            result = result.next;
        }
        System.out.println("]");
    }
}
