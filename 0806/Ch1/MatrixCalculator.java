//練習 1.2：矩陣運算器

public class MatrixCalculator {

    // 矩陣加法
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        if (rows != B.length || cols != B[0].length) {
            throw new IllegalArgumentException("矩陣維度不同，無法相加");
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("矩陣無法相乘，A的列數需等於B的行數");
        }

        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // 矩陣轉置
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    // 找最大值
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
            }
        }
        return max;
    }

    // 找最小值
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) min = val;
            }
        }
        return min;
    }

    // 列印矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    // 測試主程式
    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] B = {
                {7, 8, 9},
                {10, 11, 12}
        };
        int[][] C = {
                {1, 4},
                {2, 5},
                {3, 6}
        };

        System.out.println("矩陣 A:");
        printMatrix(A);
        System.out.println("矩陣 B:");
        printMatrix(B);

        // 矩陣加法
        System.out.println("\nA + B:");
        printMatrix(add(A, B));

        // 矩陣乘法 (A × C)
        System.out.println("\nA × C:");
        printMatrix(multiply(A, C));

        // 矩陣轉置
        System.out.println("\nA 的轉置:");
        printMatrix(transpose(A));

        // 最大值 & 最小值
        System.out.println("\nA 的最大值: " + findMax(A));
        System.out.println("A 的最小值: " + findMin(A));
    }
}
