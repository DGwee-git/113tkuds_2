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

public class M07_BinaryTreeLeftView {
    
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
    
    // 計算左視圖
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // 遍歷當前層的所有節點
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                
                // 每層第一個節點就是左視圖的節點
                if (i == 0) {
                    result.add(current.val);
                }
                
                // 加入下一層的節點
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        return result;
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
        
        // 計算左視圖
        List<Integer> leftViewNodes = leftView(root);
        
        // 輸出結果
        System.out.print("LeftView:");
        for (int val : leftViewNodes) {
            System.out.print(" " + val);
        }
        System.out.println();
        
        scanner.close();
    }
}
