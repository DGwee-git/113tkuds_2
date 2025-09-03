import java.util.*;

public class lt_13_RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1); map.put('V', 5); map.put('X', 10);
        map.put('L', 50); map.put('C', 100);
        map.put('D', 500); map.put('M', 1000);

        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i < s.length() - 1 && value < map.get(s.charAt(i + 1))) {
                total -= value; // 減法情況
            } else {
                total += value;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        lt_13_RomanToInteger solution = new lt_13_RomanToInteger();
        System.out.println(solution.romanToInt("III"));     // 預期 3
        System.out.println(solution.romanToInt("LVIII"));   // 預期 58
        System.out.println(solution.romanToInt("MCMXCIV")); // 預期 1994
    }
}

/*
解題思路：
- 建立字母與數字映射。
- 從左到右遍歷：
  - 若當前數字 < 下一個數字，則減去。
  - 否則加上。
*/
