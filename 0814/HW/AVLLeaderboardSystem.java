// 練習 5：AVL 樹應用 - 排行榜系統
import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        int playerId;
        int score;
        int height;
        int size; // 子樹大小
        Node left, right;

        Node(int playerId, int score) {
            this.playerId = playerId;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    static class AVLTree {
        Node root;
        Map<Integer, Node> playerMap = new HashMap<>(); // playerId -> Node (方便更新)

        // ===== 基本輔助函數 =====
        int height(Node node) {
            return (node == null) ? 0 : node.height;
        }

        int size(Node node) {
            return (node == null) ? 0 : node.size;
        }

        void update(Node node) {
            if (node != null) {
                node.height = 1 + Math.max(height(node.left), height(node.right));
                node.size = 1 + size(node.left) + size(node.right);
            }
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

            update(x);
            update(y);

            return y;
        }

        // 右旋
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            x.right = y;
            y.left = T2;

            update(y);
            update(x);

            return x;
        }

        // ===== 插入 (依照分數排序，高分在左邊) =====
        Node insert(Node node, int playerId, int score) {
            if (node == null) return new Node(playerId, score);

            if (score > node.score || (score == node.score && playerId < node.playerId)) {
                node.left = insert(node.left, playerId, score);
            } else {
                node.right = insert(node.right, playerId, score);
            }

            update(node);

            int balance = getBalance(node);

            // LL
            if (balance > 1 && score > node.left.score) return rightRotate(node);

            // RR
            if (balance < -1 && score < node.right.score) return leftRotate(node);

            // LR
            if (balance > 1 && score < node.left.score) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL
            if (balance < -1 && score > node.right.score) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        void addPlayer(int playerId, int score) {
            root = insert(root, playerId, score);
            playerMap.put(playerId, new Node(playerId, score)); // 紀錄玩家
        }

        // ===== 刪除節點 (用於更新分數時重新插入) =====
        Node delete(Node node, int playerId, int score) {
            if (node == null) return null;

            if (score > node.score || (score == node.score && playerId < node.playerId)) {
                node.left = delete(node.left, playerId, score);
            } else if (score < node.score || (score == node.score && playerId > node.playerId)) {
                node.right = delete(node.right, playerId, score);
            } else {
                // 找到要刪的節點
                if (node.left == null || node.right == null) {
                    node = (node.left != null) ? node.left : node.right;
                } else {
                    Node successor = minValueNode(node.right);
                    node.playerId = successor.playerId;
                    node.score = successor.score;
                    node.right = delete(node.right, successor.playerId, successor.score);
                }
            }

            if (node == null) return null;

            update(node);

            int balance = getBalance(node);

            // LL
            if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);

            // LR
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RR
            if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);

            // RL
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) current = current.left;
            return current;
        }

        // 更新分數
        void updateScore(int playerId, int newScore) {
            Node oldNode = playerMap.get(playerId);
            if (oldNode == null) return;

            root = delete(root, oldNode.playerId, oldNode.score);
            root = insert(root, playerId, newScore);
            playerMap.put(playerId, new Node(playerId, newScore));
        }

        // ===== 查詢玩家排名 (1-based，高分排名靠前) =====
        int getRank(Node node, int score, int playerId) {
            if (node == null) return 0;

            if (score > node.score || (score == node.score && playerId < node.playerId)) {
                return getRank(node.left, score, playerId);
            } else if (score < node.score || (score == node.score && playerId > node.playerId)) {
                return size(node.left) + 1 + getRank(node.right, score, playerId);
            } else {
                return size(node.left) + 1;
            }
        }

        int getRank(int playerId) {
            Node player = playerMap.get(playerId);
            if (player == null) return -1;
            return getRank(root, player.score, player.playerId);
        }

        // ===== 查詢前 K 名玩家 =====
        void getTopK(Node node, int k, List<Integer> result) {
            if (node == null || result.size() >= k) return;

            getTopK(node.left, k, result); // 左邊 (高分)
            if (result.size() < k) {
                result.add(node.playerId);
                getTopK(node.right, k, result);
            }
        }

        List<Integer> getTopK(int k) {
            List<Integer> result = new ArrayList<>();
            getTopK(root, k, result);
            return result;
        }
    }

    // 測試
    public static void main(String[] args) {
        AVLTree leaderboard = new AVLTree();

        leaderboard.addPlayer(1, 50);
        leaderboard.addPlayer(2, 70);
        leaderboard.addPlayer(3, 60);
        leaderboard.addPlayer(4, 90);
        leaderboard.addPlayer(5, 80);

        System.out.println("玩家 3 的排名: " + leaderboard.getRank(3)); // 3
        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));   // [4, 5, 2]

        System.out.println("\n更新玩家 3 的分數 (95)");
        leaderboard.updateScore(3, 95);

        System.out.println("玩家 3 的排名: " + leaderboard.getRank(3)); // 1
        System.out.println("前 3 名玩家: " + leaderboard.getTopK(3));   // [3, 4, 5]
    }
}
