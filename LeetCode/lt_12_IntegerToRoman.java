public class lt_12_IntegerToRoman {
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        lt_12_IntegerToRoman solution = new lt_12_IntegerToRoman();
        System.out.println(solution.intToRoman(3749)); // 預期 "MMMDCCXLIX"
        System.out.println(solution.intToRoman(58));   // 預期 "LVIII"
        System.out.println(solution.intToRoman(1994)); // 預期 "MCMXCIV"
    }
}

/*
解題思路：
- 準備數字與羅馬符號對應表。
- 從大到小，依序減去並拼接。
- 確保處理特殊情況 (4, 9, 40, 90, 400, 900)。
*/
