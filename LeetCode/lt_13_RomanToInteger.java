import java.util.*;

public class lt_13_RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                sum -= value; // 遇到 IV, IX, XL, XC, CD, CM
            } else {
                sum += value;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        lt_13_RomanToInteger solution = new lt_13_RomanToInteger();
        String[] tests = {"III", "LVIII", "MCMXCIV", "MMMDCCXLIX"};

        for (String s : tests) {
            System.out.println("Input: " + s);
            System.out.println("Output: " + solution.romanToInt(s));
            System.out.println();
        }
    }
}
