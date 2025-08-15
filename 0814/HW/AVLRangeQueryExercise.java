// 練習 4：範圍查詢
import java.util.*;

public class AVLRangeQueryExercise {

    // 節點定義
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class AVLTree {
        Node root;

        // ===== 基本輔助函數 =====
        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        void updateHeight(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        // 左旋
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            y.left = x;
            x.right = T2;

            updateHeight(x);
            updateHeight(y);

            return y;
        }

        // 右旋
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            updateHeight(y);
            updateHeight(x);

            return x;
        }

        // 插入
        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node;

            updateHeight(node);

            int balance = getBalance(node);

            // LL
            if (balance > 1 && key < node.left.key) return rightRotate(node);

            // RR
            if (balance < -1 && key > node.right.key) return leftRotate(node);

            // LR
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void insert(int key) {
            root = insert(root, key);
        }

        // ===== 範圍查詢 =====
        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQuery(root, min, max, result);
            return result;
        }

        private void rangeQuery(Node node, int min, int max, List<Integer> result) {
            if (node == null) return;

            // 剪枝：左子樹可能有解
            if (node.key > min) {
                rangeQuery(node.left, min, max, result);
            }

            // 當前節點在範圍內
            if (node.key >= min && node.key <= max) {
                result.add(node.key);
            }

            // 剪枝：右子樹可能有解
            if (node.key < max) {
                rangeQuery(node.right, min, max, result);
            }
        }

        // 中序走訪 (檢查用)
        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }

        void printInorder() {
            inorder(root);
            System.out.println();
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int k : keys) tree.insert(k);

        System.out.println("中序遍歷:");
        tree.printInorder(); // 5 10 15 20 25 30 35

        System.out.println("\n範圍查詢 [12, 28]:");
        System.out.println(tree.rangeQuery(12, 28)); // [15, 20, 25]

        System.out.println("\n範圍查詢 [5, 35]:");
        System.out.println(tree.rangeQuery(5, 35)); // [5, 10, 15, 20, 25, 30, 35]

        System.out.println("\n範圍查詢 [16, 19]:");
        System.out.println(tree.rangeQuery(16, 19)); // []
    }
}
