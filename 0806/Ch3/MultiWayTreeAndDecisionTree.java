//練習 3.10：多路樹與決策樹

import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // 多路樹節點
    static class MultiNode {
        String val;
        List<MultiNode> children;

        MultiNode(String val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        void addChild(MultiNode child) {
            children.add(child);
        }
    }

    // 1. 多路樹深度優先走訪 (DFS)
    public static void dfs(MultiNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiNode child : root.children) {
            dfs(child);
        }
    }

    // 2. 多路樹廣度優先走訪 (BFS)
    public static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            MultiNode node = queue.poll();
            System.out.print(node.val + " ");
            for (MultiNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 3. 簡單猜數字遊戲決策樹示範
    // 節點為判斷或答案，判斷分支代表玩家猜測結果
    static class DecisionNode {
        String question;  // null 表示葉節點（答案）
        Map<String, DecisionNode> branches;

        DecisionNode(String question) {
            this.question = question;
            this.branches = new HashMap<>();
        }

        void addBranch(String answer, DecisionNode node) {
            branches.put(answer, node);
        }

        boolean isLeaf() {
            return question == null;
        }
    }

    public static void playGuessNumberGame(Scanner scanner) {
        // 簡易決策樹:
        // 根: "Is number > 50?"
        // 左: "Is number > 25?"
        // 依此類推...
        DecisionNode root = new DecisionNode("Is number > 50?");
        DecisionNode node25 = new DecisionNode("Is number > 25?");
        DecisionNode node75 = new DecisionNode("Is number > 75?");
        DecisionNode leaf10 = new DecisionNode(null);
        DecisionNode leaf20 = new DecisionNode(null);
        DecisionNode leaf60 = new DecisionNode(null);
        DecisionNode leaf80 = new DecisionNode(null);

        leaf10.question = "Your number is 10";
        leaf20.question = "Your number is 20";
        leaf60.question = "Your number is 60";
        leaf80.question = "Your number is 80";

        root.addBranch("yes", node75);
        root.addBranch("no", node25);

        node25.addBranch("yes", leaf20);
        node25.addBranch("no", leaf10);

        node75.addBranch("yes", leaf80);
        node75.addBranch("no", leaf60);

        DecisionNode current = root;
        while(!current.isLeaf()) {
            System.out.println(current.question + " (yes/no)");
            String ans = scanner.nextLine().trim().toLowerCase();
            if (!current.branches.containsKey(ans)) {
                System.out.println("Please answer 'yes' or 'no'.");
                continue;
            }
            current = current.branches.get(ans);
        }
        System.out.println(current.question);
    }

    // 4. 多路樹高度 (最大深度)
    public static int multiTreeHeight(MultiNode root) {
        if (root == null) return 0;
        int maxChildHeight = 0;
        for (MultiNode child : root.children) {
            maxChildHeight = Math.max(maxChildHeight, multiTreeHeight(child));
        }
        return maxChildHeight + 1;
    }

    // 5. 計算每節點度數 (子節點數)
    public static void printNodeDegrees(MultiNode root) {
        if (root == null) return;
        System.out.println("Node " + root.val + " degree: " + root.children.size());
        for (MultiNode child : root.children) {
            printNodeDegrees(child);
        }
    }

    // 測試
    public static void main(String[] args) {
        // 建立多路樹
        MultiNode root = new MultiNode("A");
        MultiNode b = new MultiNode("B");
        MultiNode c = new MultiNode("C");
        MultiNode d = new MultiNode("D");
        MultiNode e = new MultiNode("E");
        MultiNode f = new MultiNode("F");

        root.addChild(b);
        root.addChild(c);
        root.addChild(d);
        b.addChild(e);
        b.addChild(f);

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();

        System.out.print("BFS: ");
        bfs(root);
        System.out.println();

        System.out.println("多路樹高度: " + multiTreeHeight(root));
        System.out.println("每節點度數:");
        printNodeDegrees(root);

        // 決策樹猜數字遊戲
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n開始猜數字遊戲");
        playGuessNumberGame(scanner);
        scanner.close();
    }
}

