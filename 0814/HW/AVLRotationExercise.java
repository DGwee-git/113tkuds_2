// 練習 2：旋轉操作實作
public class AVLRotationExercise {

    // 節點定義
    static class Node {
        int key;
        Node left, right;
        int height; // 需要存高度，旋轉時要更新

        Node(int key) {
            this.key = key;
            this.height = 1; // 新節點高度 = 1
        }
    }

    static class AVLTree {
        Node root;

        // 計算高度
        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        // 計算平衡因子
        int getBalance(Node node) {
            return (node == null) ? 0 : height(node.left) - height(node.right);
        }

        // 更新節點高度
        void updateHeight(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        // ===== 四種旋轉操作 =====

        // 右旋 (Right Rotation, LL 情況)
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            // 執行旋轉
            x.right = y;
            y.left = T2;

            // 更新高度
            updateHeight(y);
            updateHeight(x);

            return x; // 新根
        }

        // 左旋 (Left Rotation, RR 情況)
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            // 執行旋轉
            y.left = x;
            x.right = T2;

            // 更新高度
            updateHeight(x);
            updateHeight(y);

            return y; // 新根
        }

        // 插入節點 (包含旋轉平衡)
        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else
                return node; // 不允許重複

            // 更新高度
            updateHeight(node);

            // 檢查平衡
            int balance = getBalance(node);

            // LL (左左)
            if (balance > 1 && key < node.left.key)
                return rightRotate(node);

            // RR (右右)
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);

            // LR (左右)
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL (右左)
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void insert(int key) {
            root = insert(root, key);
        }

        // 中序走訪 (檢查結果用)
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

        // 測試 LL (右旋)
        System.out.println("測試 LL (插入 30,20,10):");
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.printInorder(); // 10 20 30

        // 測試 RR (左旋)
        tree = new AVLTree();
        System.out.println("測試 RR (插入 10,20,30):");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.printInorder(); // 10 20 30

        // 測試 LR (左右旋)
        tree = new AVLTree();
        System.out.println("測試 LR (插入 30,10,20):");
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);
        tree.printInorder(); // 10 20 30

        // 測試 RL (右左旋)
        tree = new AVLTree();
        System.out.println("測試 RL (插入 10,30,20):");
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);
        tree.printInorder(); // 10 20 30
    }
}
