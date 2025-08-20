import java.util.*;

class RBNode {
    int val;
    char color; // 'B' for Black, 'R' for Red
    RBNode left;
    RBNode right;
    int index; // 原始陣列索引，用於錯誤報告
    
    RBNode(int val, char color, int index) {
        this.val = val;
        this.color = color;
        this.index = index;
        this.left = null;
        this.right = null;
    }
}

public class M10_RBPropertiesCheck {
    
    // 根據層序遍歷陣列建立紅黑樹
    public static RBNode buildRBTree(int[] values, char[] colors) {
        if (values.length == 0 || values[0] == -1) {
            return null;
        }
        
        RBNode[] nodes = new RBNode[values.length];
        
        // 建立所有非空節點
        for (int i = 0; i < values.length; i++) {
            if (values[i] != -1) {
                nodes[i] = new RBNode(values[i], colors[i], i);
            }
        }
        
        // 建立父子關係
        for (int i = 0; i < values.length; i++) {
            if (nodes[i] != null) {
                int leftIndex = 2 * i + 1;
                int rightIndex = 2 * i + 2;
                
                if (leftIndex < values.length) {
                    nodes[i].left = nodes[leftIndex];
                }
                if (rightIndex < values.length) {
                    nodes[i].right = nodes[rightIndex];
                }
            }
        }
        
        return nodes[0];
    }
    
    // 檢查性質1：根節點為黑色
    public static String checkRootBlack(RBNode root) {
        if (root != null && root.color != 'B') {
            return "RootNotBlack";
        }
        return null;
    }
    
    // 檢查性質2：不得有相鄰紅節點
    public static String checkRedRedViolation(RBNode root) {
        return checkRedRedHelper(root);
    }
    
    private static String checkRedRedHelper(RBNode node) {
        if (node == null) {
            return null;
        }
        
        // 如果當前節點是紅色，檢查其子節點
        if (node.color == 'R') {
            if ((node.left != null && node.left.color == 'R') ||
                (node.right != null && node.right.color == 'R')) {
                return "RedRedViolation at index " + node.index;
            }
        }
        
        // 遞迴檢查左右子樹
        String leftResult = checkRedRedHelper(node.left);
        if (leftResult != null) {
            return leftResult;
        }
        
        String rightResult = checkRedRedHelper(node.right);
        if (rightResult != null) {
            return rightResult;
        }
        
        return null;
    }
    
    // 檢查性質3：黑高度一致
    public static String checkBlackHeight(RBNode root) {
        int blackHeight = calculateBlackHeight(root);
        return blackHeight == -1 ? "BlackHeightMismatch" : null;
    }
    
    // 計算黑高度，如果不一致返回-1
    private static int calculateBlackHeight(RBNode node) {
        if (node == null) {
            return 0; // NIL節點被視為黑色，高度為0
        }
        
        // 遞迴計算左右子樹的黑高度
        int leftBlackHeight = calculateBlackHeight(node.left);
        int rightBlackHeight = calculateBlackHeight(node.right);
        
        // 如果子樹已經不一致，直接返回-1
        if (leftBlackHeight == -1 || rightBlackHeight == -1) {
            return -1;
        }
        
        // 如果左右子樹黑高度不同，返回-1
        if (leftBlackHeight != rightBlackHeight) {
            return -1;
        }
        
        // 如果當前節點是黑色，黑高度加1
        int currentBlackHeight = leftBlackHeight;
        if (node.color == 'B') {
            currentBlackHeight++;
        }
        
        return currentBlackHeight;
    }
    
    // 完整檢查紅黑樹性質
    public static String validateRBProperties(RBNode root) {
        // 檢查性質1：根節點為黑色
        String result = checkRootBlack(root);
        if (result != null) {
            return result;
        }
        
        // 檢查性質2：不得有相鄰紅節點
        result = checkRedRedViolation(root);
        if (result != null) {
            return result;
        }
        
        // 檢查性質3：黑高度一致
        result = checkBlackHeight(root);
        if (result != null) {
            return result;
        }
        
        return "RB Valid";
    }
    
    // 輔助方法：打印樹結構（調試用）
    public static void printTree(RBNode root, String prefix, boolean isLast) {
        if (root != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + 
                             root.val + "(" + root.color + ")");
            
            List<RBNode> children = new ArrayList<>();
            if (root.left != null || root.right != null) {
                children.add(root.left);
                children.add(root.right);
            }
            
            for (int i = 0; i < children.size(); i++) {
                boolean isLastChild = (i == children.size() - 1);
                String newPrefix = prefix + (isLast ? "    " : "│   ");
                if (children.get(i) != null) {
                    printTree(children.get(i), newPrefix, isLastChild);
                } else {
                    System.out.println(newPrefix + (isLastChild ? "└── " : "├── ") + "NIL(B)");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int[] values = new int[n];
        char[] colors = new char[n];
        
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            String colorStr = scanner.next();
            colors[i] = colorStr.charAt(0);
            
            // -1 (null) 節點視為黑色
            if (values[i] == -1) {
                colors[i] = 'B';
            }
        }
        
        // 建立紅黑樹
        RBNode root = buildRBTree(values, colors);
        
        // 檢查紅黑樹性質
        String result = validateRBProperties(root);
        
        // 輸出結果
        System.out.println(result);
        
        scanner.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：checkRootBlack 為 O(1) 常數時間檢查
 *       checkRedRedViolation 需要遍歷每個節點檢查紅紅相鄰，時間複雜度 O(n)
 *       checkBlackHeight 使用後序遍歷計算每個節點的黑高度，時間複雜度 O(n)
 *       總時間複雜度為 O(1) + O(n) + O(n) = O(n)，其中 n 為樹的節點數
 */