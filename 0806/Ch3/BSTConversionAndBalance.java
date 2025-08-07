//練習 3.9：BST轉換與平衡

public class BSTConversionAndBalance {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 1. BST轉排序雙向鏈結串列 (中序遍歷連結節點)
    static TreeNode head = null;  // 雙向鏈表頭
    static TreeNode prev = null;  // 上一個節點

    public static TreeNode bstToDoublyList(TreeNode root) {
        head = prev = null;
        inorderDoublyList(root);
        return head;
    }

    private static void inorderDoublyList(TreeNode node) {
        if (node == null) return;

        inorderDoublyList(node.left);

        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        inorderDoublyList(node.right);
    }

    // 2. 排序陣列轉平衡BST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length -1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid -1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    // 3. 檢查BST是否平衡 並計算平衡因子 (左右子樹高度差)
    // 回傳樹高度，若不平衡回傳 -1
    public static boolean isBalancedBST(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 計算平衡因子 (左子樹高度 - 右子樹高度)
    public static int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // 4. 將BST節點值改為所有 >= 該節點值的總和 (逆中序遍歷累加)
    static int accSum = 0;
    public static void convertBST(TreeNode root) {
        accSum = 0;
        reverseInorder(root);
    }

    private static void reverseInorder(TreeNode node) {
        if (node == null) return;
        reverseInorder(node.right);
        accSum += node.val;
        node.val = accSum;
        reverseInorder(node.left);
    }

    // 輔助：中序遍歷列印BST
    public static void inorderPrint(TreeNode root) {
        if (root == null) return;
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 輔助：雙向鏈表列印（從頭開始往右）
    public static void printDoublyList(TreeNode head) {
        TreeNode cur = head;
        while(cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    // 測試
    public static void main(String[] args) {
        /*
                5
               / \
              3   7
             / \   \
            2   4   8
        */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);

        System.out.print("原BST中序: ");
        inorderPrint(root);
        System.out.println();

        // 1. BST轉雙向鏈結串列
        TreeNode head = bstToDoublyList(root);
        System.out.print("雙向鏈結串列: ");
        printDoublyList(head);

        // 2. 排序陣列轉平衡BST
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode balancedRoot = sortedArrayToBST(sortedArr);
        System.out.print("排序陣列轉平衡BST中序: ");
        inorderPrint(balancedRoot);
        System.out.println();

        // 3. 判斷是否平衡BST，及計算根節點平衡因子
        System.out.println("原BST是否平衡? " + isBalancedBST(root));
        System.out.println("根節點平衡因子: " + balanceFactor(root));

        // 4. 節點值改為 >= 自己的節點值總和
        convertBST(root);
        System.out.print("改造後BST中序: ");
        inorderPrint(root);
        System.out.println();
    }
}

