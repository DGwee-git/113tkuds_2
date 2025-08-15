// 練習 1：實作基本操作
public class AVLBasicExercise {

    // 節點定義
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    static class AVLTree {
        Node root;

        // 插入節點 (標準 BST 插入)
        public Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }
            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }
            return node;
        }

        public void insert(int key) {
            root = insert(root, key);
        }

        // 搜尋節點
        public boolean search(Node node, int key) {
            if (node == null) return false;
            if (key == node.key) return true;
            if (key < node.key) return search(node.left, key);
            else return search(node.right, key);
        }

        public boolean search(int key) {
            return search(root, key);
        }

        // 計算高度
        public int height(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(height(node.left), height(node.right));
        }

        public int height() {
            return height(root);
        }

        // 檢查是否為有效的 AVL 樹
        public boolean isAVL(Node node) {
            if (node == null) return true;

            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            int balance = leftHeight - rightHeight;

            if (balance < -1 || balance > 1) return false;

            return isAVL(node.left) && isAVL(node.right);
        }

        public boolean isAVL() {
            return isAVL(root);
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // 插入一些節點
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        // 測試搜尋
        System.out.println("搜尋 30: " + tree.search(30)); // true
        System.out.println("搜尋 15: " + tree.search(15)); // false

        // 測試高度
        System.out.println("樹的高度: " + tree.height());

        // 檢查是否為 AVL 樹
        System.out.println("是否為 AVL 樹: " + tree.isAVL());
    }
}
