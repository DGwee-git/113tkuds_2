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

public class M08_BSTRangedSum {
    
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
    
    // 計算BST在區間[L, R]內的節點值總和
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // 如果當前節點值在區間內，加入總和
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        
        // 利用BST性質進行剪枝
        // 如果當前節點值大於R，只需搜尋左子樹
        if (root.val > R) {
            sum += rangeSumBST(root.left, L, R);
        }
        // 如果當前節點值小於L，只需搜尋右子樹
        else if (root.val < L) {
            sum += rangeSumBST(root.right, L, R);
        }
        // 如果當前節點值在區間內，需要搜尋左右子樹
        else {
            sum += rangeSumBST(root.left, L, R);
            sum += rangeSumBST(root.right, L, R);
        }
        
        return sum;
    }
    
    // 迭代版本的區間總和計算（可選）
    public static int rangeSumBSTIterative(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            
            if (current == null) {
                continue;
            }
            
            // 如果當前節點值在區間內，加入總和
            if (current.val >= L && current.val <= R) {
                sum += current.val;
            }
            
            // 利用BST性質進行剪枝
            if (current.val > L && current.left != null) {
                stack.push(current.left);
            }
            if (current.val < R && current.right != null) {
                stack.push(current.right);
            }
        }
        
        return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 讀取輸入
        int n = scanner.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        
        // 建立BST
        TreeNode root = buildTree(arr);
        
        // 計算區間總和
        int sum = rangeSumBST(root, L, R);
        
        // 輸出結果
        System.out.println("Sum: " + sum);
        
        scanner.close();
    }
}

/*
 * Time Complexity: O(n) 最壞情況，O(log n + k) 平均情況
 * 說明：在最壞情況下（不平衡的BST），需要訪問所有節點，時間複雜度為O(n)
 *       在平均情況下（平衡的BST），利用BST性質剪枝，只需訪問O(log n)個節點加上區間內的k個節點
 *       空間複雜度為O(h)，其中h為樹的高度，用於遞歸調用棧
 */