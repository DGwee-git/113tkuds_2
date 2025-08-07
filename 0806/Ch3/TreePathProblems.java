//練習 3.7：樹的路徑問題

import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 找出從根到所有葉節點的路徑
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        dfsPaths(root, current, paths);
        return paths;
    }

    private static void dfsPaths(TreeNode node, List<Integer> current, List<List<Integer>> paths) {
        if (node == null) return;
        current.add(node.val);
        if (node.left == null && node.right == null) {
            paths.add(new ArrayList<>(current));
        } else {
            dfsPaths(node.left, current, paths);
            dfsPaths(node.right, current, paths);
        }
        current.remove(current.size() - 1);
    }

    // 2. 判斷是否存在根到葉路徑和為目標值
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑
    public static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 4. 計算樹中任意兩節點間的最大路徑和（樹的直徑）
    static int maxPathSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSum;
    }

    // helper: max gain from node, 同時更新 maxPathSum
    private static int maxGain(TreeNode node) {
        if (node == null) return 0;

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int currentPathSum = node.val + leftGain + rightGain;
        maxPathSum = Math.max(maxPathSum, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }

    // 測試
    public static void main(String[] args) {
        /*
                 1
                / \
               2   3
              / \   \
             4   5   6
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("所有根到葉路徑: " + allRootToLeafPaths(root));
        System.out.println("存在和為 7 的路徑? " + hasPathSum(root, 7));
        System.out.println("存在和為 10 的路徑? " + hasPathSum(root, 10));
        System.out.println("最大根到葉路徑和: " + maxRootToLeafSum(root));
        System.out.println("樹的最大路徑和: " + maxPathSum(root));
    }
}

