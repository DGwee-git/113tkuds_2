//練習 3.5：層序走訪變形

import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 1. 將每層節點分別儲存在不同List中
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    // 2. 之字形層序走訪
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if(leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            result.add(new ArrayList<>(level));
            leftToRight = !leftToRight;
        }
        return result;
    }

    // 3. 只列印每層的最後一個節點
    public static List<Integer> lastNodeEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;

            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                lastVal = node.val;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            result.add(lastVal);
        }
        return result;
    }

    // 4. 垂直層序走訪
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        // key: column index, value: list of nodes
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            map.computeIfAbsent(p.col, k -> new ArrayList<>()).add(p.node.val);

            if(p.node.left != null) queue.offer(new Pair(p.node.left, p.col - 1));
            if(p.node.right != null) queue.offer(new Pair(p.node.right, p.col + 1));
        }

        for(List<Integer> colNodes : map.values()) {
            result.add(colNodes);
        }
        return result;
    }

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode n, int c) {
            node = n;
            col = c;
        }
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

        System.out.println("每層節點分組: " + levelOrder(root));
        System.out.println("之字形走訪: " + zigzagLevelOrder(root));
        System.out.println("每層最後一個節點: " + lastNodeEachLevel(root));
        System.out.println("垂直層序走訪: " + verticalOrder(root));
    }
}
