//練習 3.2：BST範圍查詢系統

import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 插入節點
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    // 1. 範圍查詢：找出所有在 [min, max] 範圍的節點
    public static List<Integer> rangeQuery(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, min, max, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) return;
        if (root.val > min) rangeQueryHelper(root.left, min, max, res);
        if (root.val >= min && root.val <= max) res.add(root.val);
        if (root.val < max) rangeQueryHelper(root.right, min, max, res);
    }

    // 2. 範圍計數
    public static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeCount(root.right, min, max);
        if (root.val > max) return rangeCount(root.left, min, max);
        return 1 + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    // 3. 範圍總和
    public static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        if (root.val < min) return rangeSum(root.right, min, max);
        if (root.val > max) return rangeSum(root.left, min, max);
        return root.val + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    // 4. 找最接近給定值的節點
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        TreeNode current = root;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            if (target < current.val) {
                current = current.left;
            } else if (target > current.val) {
                current = current.right;
            } else {
                return current.val; // exact match
            }
        }
        return closest;
    }

    // 測試
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode root = null;
        for (int v : values) {
            root = insert(root, v);
        }

        int min = 10, max = 30;
        System.out.println("範圍查詢 [" + min + ", " + max + "]: " + rangeQuery(root, min, max));
        System.out.println("範圍計數 [" + min + ", " + max + "]: " + rangeCount(root, min, max));
        System.out.println("範圍總和 [" + min + ", " + max + "]: " + rangeSum(root, min, max));

        int target = 28;
        System.out.println("最接近 " + target + " 的節點值: " + closestValue(root, target));
    }
}
