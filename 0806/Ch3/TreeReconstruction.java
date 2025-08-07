//練習 3.8：樹的重建

import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 根據前序與中序重建
    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return buildPreInHelper(preorder, 0, preorder.length - 1,
                               inorder, 0, inorder.length - 1, inIndexMap);
    }

    private static TreeNode buildPreInHelper(int[] pre, int preStart, int preEnd,
                                             int[] in, int inStart, int inEnd,
                                             Map<Integer, Integer> inIndexMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPreInHelper(pre, preStart + 1, preStart + leftTreeSize,
                                    in, inStart, inRootIndex - 1, inIndexMap);
        root.right = buildPreInHelper(pre, preStart + leftTreeSize + 1, preEnd,
                                     in, inRootIndex + 1, inEnd, inIndexMap);

        return root;
    }

    // 2. 根據後序與中序重建
    public static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inIndexMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            inIndexMap.put(inorder[i], i);
        }
        return buildPostInHelper(postorder, 0, postorder.length-1,
                                inorder, 0, inorder.length-1, inIndexMap);
    }

    private static TreeNode buildPostInHelper(int[] post, int postStart, int postEnd,
                                              int[] in, int inStart, int inEnd,
                                              Map<Integer, Integer> inIndexMap) {
        if(postStart > postEnd || inStart > inEnd) return null;
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);

        int inRootIndex = inIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildPostInHelper(post, postStart, postStart + leftTreeSize - 1,
                                     in, inStart, inRootIndex - 1, inIndexMap);
        root.right = buildPostInHelper(post, postStart + leftTreeSize, postEnd - 1,
                                      in, inRootIndex + 1, inEnd, inIndexMap);

        return root;
    }

    // 3. 根據層序走訪結果重建完全二元樹
    public static TreeNode buildCompleteTreeByLevel(int[] levelOrder) {
        if(levelOrder.length == 0) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while(i < levelOrder.length) {
            TreeNode current = queue.poll();
            if(i < levelOrder.length) {
                current.left = new TreeNode(levelOrder[i++]);
                queue.offer(current.left);
            }
            if(i < levelOrder.length) {
                current.right = new TreeNode(levelOrder[i++]);
                queue.offer(current.right);
            }
        }
        return root;
    }

    // 4. 驗證兩棵樹是否相同
    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        if(t1.val != t2.val) return false;
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    // 輔助：取得樹的前序走訪結果
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;
    }

    private static void preorderHelper(TreeNode node, List<Integer> res) {
        if(node == null) return;
        res.add(node.val);
        preorderHelper(node.left, res);
        preorderHelper(node.right, res);
    }

    // 輔助：取得樹的中序走訪結果
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if(node == null) return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    // 測試
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        int[] levelorder = {3,9,20,15,7};

        TreeNode treeFromPreIn = buildTreePreIn(preorder, inorder);
        TreeNode treeFromPostIn = buildTreePostIn(postorder, inorder);
        TreeNode treeFromLevel = buildCompleteTreeByLevel(levelorder);

        System.out.println("前序+中序重建後的前序走訪: " + preorderTraversal(treeFromPreIn));
        System.out.println("後序+中序重建後的前序走訪: " + preorderTraversal(treeFromPostIn));
        System.out.println("層序重建完全二元樹的前序走訪: " + preorderTraversal(treeFromLevel));

        // 驗證前序+中序與後序+中序重建結果是否相同
        System.out.println("前序+中序與後序+中序是否相同? " + isSameTree(treeFromPreIn, treeFromPostIn));
    }
}
