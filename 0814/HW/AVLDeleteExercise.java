// 練習 3：刪除操作實作
public class AVLDeleteExercise {

    // 節點定義
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // 初始高度 = 1
        }
    }

    static class AVLTree {
        Node root;

        // 計算高度
        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        // 更新節點高度
        void updateHeight(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }

        // 平衡因子
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

        // 插入 (用來測試刪除)
        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node; // 不允許重複

            updateHeight(node);

            return rebalance(node, key);
        }

        void insert(int key) {
            root = insert(root, key);
        }

        // 刪除節點
        Node delete(Node node, int key) {
            if (node == null) return null;

            // 1️⃣ BST 標準刪除
            if (key < node.key) {
                node.left = delete(node.left, key);
            } else if (key > node.key) {
                node.right = delete(node.right, key);
            } else {
                // 找到要刪的節點
                if (node.left == null || node.right == null) {
                    // 只有一個子或沒有子
                    Node temp = (node.left != null) ? node.left : node.right;
                    if (temp == null) {
                        // 無子 (葉子)
                        node = null;
                    } else {
                        // 一個子節點
                        node = temp;
                    }
                } else {
                    // 兩個子節點 → 找最小右子 (中序後繼)
                    Node successor = minValueNode(node.right);
                    node.key = successor.key;
                    node.right = delete(node.right, successor.key);
                }
            }

            if (node == null) return null;

            // 2️⃣ 更新高度
            updateHeight(node);

            // 3️⃣ 檢查平衡
            return rebalance(node, key);
        }

        void delete(int key) {
            root = delete(root, key);
        }

        // 找最小值 (用於刪除時的後繼)
        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        // 重新平衡
        Node rebalance(Node node, int key) {
            int balance = getBalance(node);

            // LL
            if (balance > 1 && getBalance(node.left) >= 0)
                return rightRotate(node);

            // LR
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RR
            if (balance < -1 && getBalance(node.right) <= 0)
                return leftRotate(node);

            // RL
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        // 中序走訪
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

        int[] keys = {20, 10, 30, 25, 40, 22};
        for (int k : keys) tree.insert(k);

        System.out.println("初始樹 (中序):");
        tree.printInorder(); // 10 20 22 25 30 40

        // 測試刪除葉子
        System.out.println("\n刪除葉子節點 22:");
        tree.delete(22);
        tree.printInorder();

        // 測試刪除只有一個子
        System.out.println("\n刪除只有一個子節點 40:");
        tree.delete(40);
        tree.printInorder();

        // 測試刪除有兩個子
        System.out.println("\n刪除有兩個子節點 20:");
        tree.delete(20);
        tree.printInorder();
    }
}
