//練習 3.4：BST第k小/大元素

import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val;
        TreeNode left, right;
        int size;  // 子樹大小（包含自己）

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    // 插入節點並更新 size
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }

    // 刪除節點並更新 size
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) {
            root.left = delete(root.left, val);
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else { // 找到節點刪除
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                TreeNode successor = minNode(root.right);
                root.val = successor.val;
                root.right = delete(root.right, successor.val);
            }
        }
        root.size = 1 + size(root.left) + size(root.right);
        return root;
    }

    private static TreeNode minNode(TreeNode root) {
        while (root.left != null) root = root.left;
        return root;
    }

    private static int size(TreeNode root) {
        return root == null ? 0 : root.size;
    }

    // 找第 k 小元素 (k 從 1 開始)
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) throw new IllegalArgumentException("k 超出範圍");
        int leftSize = size(root.left);
        if (k <= leftSize) return kthSmallest(root.left, k);
        else if (k == leftSize + 1) return root.val;
        else return kthSmallest(root.right, k - leftSize - 1);
    }

    // 找第 k 大元素（第 k 大 = 第 size-k+1 小）
    public static int kthLargest(TreeNode root, int k) {
        return kthSmallest(root, size(root) - k + 1);
    }

    // 找出第 k 小到第 j 小之間的所有元素（含 k 和 j）
    public static List<Integer> rangeKth(TreeNode root, int k, int j) {
        List<Integer> result = new ArrayList<>();
        inorderRange(root, k, j, result);
        return result;
    }

    private static int count = 0;  // inorder 中記錄目前第幾個元素

    private static void inorderRange(TreeNode root, int k, int j, List<Integer> res) {
        if (root == null) return;
        inorderRange(root.left, k, j, res);
        count++;
        if (count >= k && count <= j) {
            res.add(root.val);
        }
        inorderRange(root.right, k, j, res);
    }

    // 測試
    public static void main(String[] args) {
        TreeNode root = null;
        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int n : nums) root = insert(root, n);

        System.out.println("第 3 小元素: " + kthSmallest(root, 3)); // 15
        System.out.println("第 2 大元素: " + kthLargest(root, 2));  // 30

        count = 0;
        System.out.println("第 2 小到第 5 小元素: " + rangeKth(root, 2, 5)); // [10,15,20,25]

        // 測試動態插入與刪除
        root = insert(root, 12);
        root = delete(root, 30);

        count = 0;
        System.out.println("插入12,刪除30後第 3 小元素: " + kthSmallest(root, 3)); // 12
        System.out.println("插入12,刪除30後第 2 大元素: " + kthLargest(root, 2)); // 25
    }
}
