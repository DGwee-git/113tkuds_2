public class lt_09_PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        String s = Integer.toString(x);
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        lt_09_PalindromeNumber solution = new lt_09_PalindromeNumber();

        int[] tests = {121, -121, 10, 12321, 0};
        int example = 1;

        for (int x : tests) {
            System.out.println("Example " + example + ":");
            System.out.println("Input: x = " + x);
            System.out.println("Output: " + solution.isPalindrome(x));
            System.out.println();
            example++;
        }
    }
}
