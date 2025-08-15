// 練習 6：持久化 AVL 樹
import java.util.*;

public class PersistentAVLExercise {

    // 節點設為不可變 (Immutable)
    static class Node {
        final int key;
        final int height;
        final Node left, right;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
        }

        static int height(Node n) {
            return (n == null) ? 0 : n.height;
        }
    }

    static class PersistentAVL {
        List<Node> versions = new ArrayList<>(); // 保存所有版本的 root

        PersistentAVL() {
            versions.add(null); // v0: 空樹
        }

        // 獲取某版本的 root
        Node getVersion(int version) {
            if (version < 0 || version >= versions.size()) throw new IllegalArgumentException("版本不存在");
            return versions.get(version);
        }

        // 插入並產生新版本
        public int insert(int version, int key) {
            Node root = getVersion(version);
            Node newRoot = insertPersistent(root, key);
            versions.add(newRoot);
            return versions.size() - 1; // 回傳新版本號
        }

        // 持久化插入 (path copying)
        private Node insertPersistent(Node node, int key) {
            if (node == null) return new Node(key, null, null);

            if (key < node.key) {
                Node newLeft = insertPersistent(node.left, key);
                node = new Node(node.key, newLeft, node.right);
            } else if (key > node.key) {
                Node newRight = insertPersistent(node.right, key);
                node = new Node(node.key, node.left, newRight);
            } else {
                return node; // 不允許重複
            }

            // 更新平衡
            return rebalance(node);
        }

        // 平衡操作
        private Node rebalance(Node node) {
            int balance = Node.height(node.left) - Node.height(node.right);

            if (balance > 1) {
                if (Node.height(node.left.left) >= Node.height(node.left.right)) {
                    return rotateRight(node); // LL
                } else {
                    return rotateLeftRight(node); // LR
                }
            } else if (balance < -1) {
                if (Node.height(node.right.right) >= Node.height(node.right.left)) {
                    return rotateLeft(node); // RR
                } else {
                    return rotateRightLeft(node); // RL
                }
            }
            return node;
        }

        // 旋轉操作 (持久化版本)
        private Node rotateRight(Node y) {
            Node x = y.left;
            Node newRight = new Node(y.key, x.right, y.right);
            return new Node(x.key, x.left, newRight);
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node newLeft = new Node(x.key, x.left, y.left);
            return new Node(y.key, newLeft, y.right);
        }

        private Node rotateLeftRight(Node node) {
            Node newLeft = rotateLeft(node.left);
            Node newNode = new Node(node.key, newLeft, node.right);
            return rotateRight(newNode);
        }

        private Node rotateRightLeft(Node node) {
            Node newRight = rotateRight(node.right);
            Node newNode = new Node(node.key, node.left, newRight);
            return rotateLeft(newNode);
        }

        // 中序走訪 (用於驗證)
        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }

        void printVersion(int version) {
            Node root = getVersion(version);
            inorder(root);
            System.out.println();
        }
    }

    // 測試
    public static void main(String[] args) {
        PersistentAVL avl = new PersistentAVL();

        int v1 = avl.insert(0, 10); // v1: 插入 10
        int v2 = avl.insert(v1, 20); // v2: 插入 20
        int v3 = avl.insert(v2, 30); // v3: 插入 30 (會觸發旋轉)

        System.out.println("版本 1:");
        avl.printVersion(v1); // 10

        System.out.println("版本 2:");
        avl.printVersion(v2); // 10 20

        System.out.println("版本 3:");
        avl.printVersion(v3); // 10 20 30 (平衡後應該是 20 為 root)
    }
}
