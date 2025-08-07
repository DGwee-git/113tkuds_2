//練習 3.3：樹的鏡像與對稱

public class TreeMirrorAndSymmetry {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 判斷是否為對稱樹（鏡像對稱）
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
               isMirror(t1.left, t2.right) &&
               isMirror(t1.right, t2.left);
    }

    // 將樹轉為鏡像樹（原地修改）
    public static void mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    // 比較兩棵樹是否互為鏡像
    public static boolean areMirrors(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
               areMirrors(t1.left, t2.right) &&
               areMirrors(t1.right, t2.left);
    }

    // 檢查 tree2 是否為 tree1 的子樹
    public static boolean isSubtree(TreeNode tree1, TreeNode tree2) {
        if (tree2 == null) return true;
        if (tree1 == null) return false;
        if (isSameTree(tree1, tree2)) return true;
        return isSubtree(tree1.left, tree2) || isSubtree(tree1.right, tree2);
    }

    private static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    // 簡單前序印樹
    public static void printTree(TreeNode root) {
        printTreeHelper(root, 0);
    }

    private static void printTreeHelper(TreeNode node, int indent) {
        if (node == null) return;
        for (int i = 0; i < indent; i++) System.out.print("  ");
        System.out.println(node.val);
        printTreeHelper(node.left, indent + 1);
        printTreeHelper(node.right, indent + 1);
    }

    public static void main(String[] args) {
        // 建立測試樹
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹? " + isSymmetric(root1)); // true
        System.out.println("\n原樹:");
        printTree(root2);

        System.out.println("\n鏡像樹:");
        mirror(root2);
        printTree(root2);

        System.out.println("\n兩棵樹是否互為鏡像? " + areMirrors(root1, root2)); // false

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(3);
        System.out.println("root2 是否包含子樹 subTree? " + isSubtree(root2, subTree)); // false，鏡像後的root2不包含subTree
    }
}
