public class lt_06_ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currRow].append(c);

            // 遇到頂或底，方向反轉
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;

            currRow += goingDown ? 1 : -1;
        }

        // 合併所有行
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        lt_06_ZigzagConversion solution = new lt_06_ZigzagConversion();

        System.out.println("Example 1:");
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        System.out.println("Input: s = \"" + s1 + "\", numRows = " + numRows1);
        System.out.println("Output: \"" + solution.convert(s1, numRows1) + "\"\n");

        System.out.println("Example 2:");
        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        System.out.println("Input: s = \"" + s2 + "\", numRows = " + numRows2);
        System.out.println("Output: \"" + solution.convert(s2, numRows2) + "\"\n");

        System.out.println("Example 3:");
        String s3 = "A";
        int numRows3 = 1;
        System.out.println("Input: s = \"" + s3 + "\", numRows = " + numRows3);
        System.out.println("Output: \"" + solution.convert(s3, numRows3) + "\"");
    }
}
