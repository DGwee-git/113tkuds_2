import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class M09_AVLValidate {
    private static final int INVALID_HEIGHT = Integer.MIN_VALUE;
    
    // 根據層序遍歷陣列建立二元樹
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) {
            return null;
        }
        
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();
            
            // 建立左子節點
            if (i < arr.length) {
                if (arr[i] != -1) {
                    current.left = new TreeNode(arr[i]);
                    queue.offer(current.left);
                }
                i++;
            }
            
            // 建立右子節點
            if (i < arr.length) {
                if (arr[i] != -1) {
                    current.right = new TreeNode(arr[i]);
                    queue.offer(current.right);
                }
                i++;
            }
        }
        
        return root;
    }
    
    // 檢查是否為有效的BST
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode node, long minVal, long maxVal) {
        if (node == null) {
            return true;
        }
        
        // 檢查當前節點值是否在有效範圍內
        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }
        
        // 遞迴檢查左右子樹
        // 左子樹：上界為當前節點值
        // 右子樹：下界為當前節點值
        return isValidBSTHelper(node.left, minVal, node.val) &&
               isValidBSTHelper(node.right, node.val, maxVal);
    }
    
    // 檢查是否為有效的AVL樹並回傳高度
    // 如果不是有效的AVL樹，回傳INVALID_HEIGHT
    public static int checkAVL(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // 遞迴檢查左子樹
        int leftHeight = checkAVL(root.left);
        if (leftHeight == INVALID_HEIGHT) {
            return INVALID_HEIGHT;
        }
        
        // 遞迴檢查右子樹
        int rightHeight = checkAVL(root.right);
        if (rightHeight == INVALID_HEIGHT) {
            return INVALID_HEIGHT;
        }
        
        // 檢查平衡因子是否滿足AVL條件
        int balanceFactor = Math.abs(leftHeight - rightHeight);
        if (balanceFactor > 1) {
            return INVALID_HEIGHT;
        }
        
        // 回傳當前節點的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // 驗證是否為有效的AVL樹
    public static String validateAVL(TreeNode root) {
        // 首先檢查是否為有效的BST
        if (!isValidBST(root)) {
            return "Invalid BST";
        }
        
        // 再檢查是否滿足AVL條件
        if (checkAVL(root) == INVALID_HEIGHT) {
            return "Invalid AVL";
        }
        
        return "Valid";
    }
    
    // 輔助方法：計算樹的高度（用於驗證）
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    
    // 輔助方法：計算節點的平衡因子
    public static int getBalanceFactor(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入節點數量: ");
        // 讀取輸入
        int n = scanner.nextInt();
        
        System.out.print("請輸入層序遍歷陣列 (用 -1 表示空節點): ");
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        // 建立樹
        TreeNode root = buildTree(arr);
        
        // 驗證是否為有效的AVL樹
        String result = validateAVL(root);
        
        // 輸出結果
        System.out.println(result);
        
        scanner.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：isValidBST 需要訪問每個節點一次檢查BST性質，時間複雜度O(n)
 *       checkAVL 使用後序遍歷，每個節點訪問一次計算高度和平衡因子，時間複雜度O(n)
 *       總時間複雜度為 O(n) + O(n) = O(n)，其中n為樹的節點數
 */