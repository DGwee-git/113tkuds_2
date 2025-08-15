public class AVLRotations {

    // 定義 AVLNode 類別
    public static class AVLNode {
        int key;
        AVLNode left, right;
        int height;

        public AVLNode(int key) {
            this.key = key;
            this.height = 1;
        }

        public void updateHeight() {
            int leftHeight = (left != null) ? left.height : 0;
            int rightHeight = (right != null) ? right.height : 0;
            this.height = Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 右旋操作
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    // 左旋操作
    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    // main 方法測試旋轉
    public static void main(String[] args) {
        // 測試右旋
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);

        root.updateHeight();
        root.left.updateHeight();

        System.out.println("原始根節點: " + root.key); // 30
        root = rightRotate(root);
        System.out.println("右旋後根節點: " + root.key); // 20

        // 測試左旋
        AVLNode root2 = new AVLNode(10);
        root2.right = new AVLNode(20);
        root2.right.right = new AVLNode(30);

        root2.updateHeight();
        root2.right.updateHeight();

        System.out.println("原始根節點: " + root2.key); // 10
        root2 = leftRotate(root2);
        System.out.println("左旋後根節點: " + root2.key); // 20
    }
}
