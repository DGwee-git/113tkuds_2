//練習 3.6：BST驗證與修復

import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    /* 1. 驗證是否為BST */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private static boolean isValidBSTHelper(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        if ((low != null && node.val <= low) || (high != null && node.val >= high))
            return false;
        return isValidBSTHelper(node.left, low, node.val) &&
               isValidBSTHelper(node.right, node.val, high);
    }

    /* 2. 找出不符合BST規則的節點 */
    // 方法：中序遍歷找出遞增序列中不正確的節點（可能兩個錯誤節點）
    static TreeNode first = null, second = null, prev = null;

    public static List<TreeNode> findWrongNodes(TreeNode root) {
        first = second = prev = null;
        inorderFind(root);
        if (first != null && second != null)
            return Arrays.asList(first, second);
        return Collections.emptyList();
    }

    private static void inorderFind(TreeNode root) {
        if (root == null) return;
        inorderFind(root.left);

        if (prev != null && root.val <= prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;

        inorderFind(root.right);
    }

    /* 3. 修復兩節點錯誤的BST */
    public static void recoverTree(TreeNode root) {
        List<TreeNode> wrongNodes = findWrongNodes(root);
        if (wrongNodes.size() == 2) {
            int temp = wrongNodes.get(0).val;
            wrongNodes.get(0).val = wrongNodes.get(1).val;
            wrongNodes.get(1).val = temp;
        }
    }

    /* 4. 計算最少要移除多少節點讓樹變成有效BST */
    // 利用計算最大BST子樹大小，結果是: 總節點數 - 最大BST子樹大小
    static class Info {
        int size;       // 節點數量
        int maxBSTSize; // 最大BST子樹大小
        int minVal, maxVal;
        boolean isBST;

        Info(int size, int maxBSTSize, int minVal, int maxVal, boolean isBST) {
            this.size = size;
            this.maxBSTSize = maxBSTSize;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.isBST = isBST;
        }
    }

    public static int minRemoveToMakeBST(TreeNode root) {
        Info info = postOrder(root);
        return info.size - info.maxBSTSize;
    }

    private static Info postOrder(TreeNode node) {
        if (node == null) return new Info(0,0,Integer.MAX_VALUE,Integer.MIN_VALUE,true);

        Info left = postOrder(node.left);
        Info right = postOrder(node.right);

        int size = left.size + right.size + 1;

        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            int maxBSTSize = left.maxBSTSize + right.maxBSTSize + 1;
            int minVal = Math.min(node.val, left.minVal);
            int maxVal = Math.max(node.val, right.maxVal);
            return new Info(size, maxBSTSize, minVal, maxVal, true);
        }

        return new Info(size, Math.max(left.maxBSTSize, right.maxBSTSize), 0, 0, false);
    }

    // 測試
    public static void main(String[] args) {
        /*
              3
             / \
            1   4
               /
              2
        節點2與3錯誤交換導致不是BST
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("驗證BST: " + isValidBST(root)); // false

        List<TreeNode> wrong = findWrongNodes(root);
        System.out.println("錯誤節點:");
        for(TreeNode n : wrong) {
            System.out.println(n.val);
        }

        recoverTree(root);
        System.out.println("修復後驗證BST: " + isValidBST(root)); // true

        // 製造一顆非BST樹
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(15);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(12); // 不符合BST規則

        System.out.println("需移除節點數讓樹成為BST: " + minRemoveToMakeBST(root2)); // 1
    }
}

