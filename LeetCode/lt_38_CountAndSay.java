public class lt_38_CountAndSay {
    public static String countAndSay(int n) {
        if (n <= 0) return "";
        String result = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(result.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count).append(result.charAt(result.length() - 1));
            result = sb.toString();
        }

        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            System.out.println(i + " -> " + countAndSay(i));
        }
        // 1 -> 1
        // 2 -> 11
        // 3 -> 21
        // 4 -> 1211
        // 5 -> 111221
        // 6 -> 312211
    }
}
