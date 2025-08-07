//練習 3.1：二元樹基本操作練習

import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // 1. 計算節點值總和和平均值
    public static int sumNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }

    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static double averageNodes(TreeNode root) {
        int sum = sumNodes(root);
        int count = countNodes(root);
        return count == 0 ? 0 : (double) sum / count;
    }

    // 2. 找最大值和最小值節點
    public static int maxNode(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(maxNode(root.left), maxNode(root.right)));
    }

    public static int minNode(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(minNode(root.left), minNode(root.right)));
    }

    // 3. 計算樹的寬度（最大層節點數）
    public static int treeWidth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return maxWidth;
    }

    // 4. 判斷是否為完全二元樹
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean mustBeLeaf = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.left);
            } else {
                mustBeLeaf = true;
            }

            if (node.right != null) {
                if (mustBeLeaf) return false;
                queue.offer(node.right);
            } else {
                mustBeLeaf = true;
            }
        }
        return true;
    }

    // 測試程式
    public static void main(String[] args) {
        /*
               10
              /  \
             5    15
            / \     \
           3   7     18
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println("節點總和: " + sumNodes(root));
        System.out.println("節點平均值: " + averageNodes(root));
        System.out.println("最大節點值: " + maxNode(root));
        System.out.println("最小節點值: " + minNode(root));
        System.out.println("樹的最大寬度: " + treeWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }
}
