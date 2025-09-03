public class lt_12_IntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", 
            "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

    // 測試程式
    public static void main(String[] args) {
        lt_12_IntegerToRoman solution = new lt_12_IntegerToRoman();

        int num1 = 3749;
        System.out.println("Example 1:");
        System.out.println("Input: num = " + num1);
        System.out.println("Output: \"" + solution.intToRoman(num1) + "\"");
        System.out.println();

        int num2 = 58;
        System.out.println("Example 2:");
        System.out.println("Input: num = " + num2);
        System.out.println("Output: \"" + solution.intToRoman(num2) + "\"");
        System.out.println();

        int num3 = 1994;
        System.out.println("Example 3:");
        System.out.println("Input: num = " + num3);
        System.out.println("Output: \"" + solution.intToRoman(num3) + "\"");
    }
}
