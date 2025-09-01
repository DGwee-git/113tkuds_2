public class lt_08_StringToInteger {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0, n = s.length();
        // 1. 去掉前導空格
        while (i < n && s.charAt(i) == ' ') i++;

        if (i == n) return 0;

        // 2. 判斷正負號
        int sign = 1;
        if (s.charAt(i) == '+') {
            i++;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        // 3. 讀取數字
        int result = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break;

            int digit = c - '0';

            // 4. 溢出檢查
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        lt_08_StringToInteger solution = new lt_08_StringToInteger();

        String[] tests = {"42", "   -042", "1337c0d3", "0-1", "words and 987"};
        int example = 1;

        for (String s : tests) {
            System.out.println("Example " + example + ":");
            System.out.println("Input: s = \"" + s + "\"");
            System.out.println("Output: " + solution.myAtoi(s));
            System.out.println();
            example++;
        }
    }
}
