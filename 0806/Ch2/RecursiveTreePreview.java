//練習 2.5：遞迴樹狀結構預習

import java.util.*;

public class RecursiveTreePreview {

    /* ==================== 模擬資料夾 & 檔案 ==================== */
    public static class FileNode {
        String name;
        boolean isFile;
        List<FileNode> children;

        FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            this.children = new ArrayList<>();
        }

        void addChild(FileNode child) {
            if (!isFile) children.add(child);
        }
    }

    /* 1. 遞迴計算資料夾的總檔案數 */
    public static int countFiles(FileNode node) {
        if (node.isFile) return 1;
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    /* 2. 遞迴列印多層選單結構 */
    public static void printMenu(FileNode node, String indent) {
        System.out.println(indent + "- " + node.name);
        for (FileNode child : node.children) {
            printMenu(child, indent + "  ");
        }
    }

    /* 3. 遞迴展平巢狀陣列 */
    public static List<Integer> flattenList(Object[] nestedArray) {
        List<Integer> result = new ArrayList<>();
        flattenHelper(nestedArray, result);
        return result;
    }

    private static void flattenHelper(Object[] arr, List<Integer> result) {
        for (Object element : arr) {
            if (element instanceof Integer) {
                result.add((Integer) element);
            } else if (element instanceof Object[]) {
                flattenHelper((Object[]) element, result);
            }
        }
    }

    /* 4. 遞迴計算巢狀清單的最大深度 */
    public static int maxDepth(Object[] nestedArray) {
        int depth = 1;
        for (Object element : nestedArray) {
            if (element instanceof Object[]) {
                depth = Math.max(depth, 1 + maxDepth((Object[]) element));
            }
        }
        return depth;
    }

    /* 測試主程式 */
    public static void main(String[] args) {
        /* 測試 1 & 2: 建立模擬檔案系統 */
        FileNode root = new FileNode("Root", false);
        FileNode folderA = new FileNode("FolderA", false);
        FileNode folderB = new FileNode("FolderB", false);
        FileNode file1 = new FileNode("file1.txt", true);
        FileNode file2 = new FileNode("file2.txt", true);
        FileNode file3 = new FileNode("file3.txt", true);

        folderA.addChild(file1);
        folderA.addChild(file2);
        folderB.addChild(file3);
        root.addChild(folderA);
        root.addChild(folderB);

        System.out.println("=== 1. 資料夾總檔案數 ===");
        System.out.println("總檔案數: " + countFiles(root));

        System.out.println("\n=== 2. 多層選單結構 ===");
        printMenu(root, "");

        /* 測試 3 & 4: 巢狀陣列 */
        Object[] nestedArray = {1, new Object[]{2, 3, new Object[]{4, 5}}, 6};

        System.out.println("\n=== 3. 展平巢狀陣列 ===");
        System.out.println(flattenList(nestedArray));

        System.out.println("\n=== 4. 巢狀清單最大深度 ===");
        System.out.println("最大深度: " + maxDepth(nestedArray));
    }
}
