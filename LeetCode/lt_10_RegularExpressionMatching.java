public class lt_10_RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        // 初始化空字串匹配模式
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i-1), pc = p.charAt(j-1);

                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j-2]; // * 代表 0 次
                    char pPrev = p.charAt(j-2);
                    if (pPrev == sc || pPrev == '.') {
                        dp[i][j] = dp[i][j] || dp[i-1][j]; // * 代表 >=1 次
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        lt_10_RegularExpressionMatching solution = new lt_10_RegularExpressionMatching();

        String[][] tests = {
            {"aa", "a"},
            {"aa", "a*"},
            {"ab", ".*"},
            {"mississippi", "mis*is*p*"}
        };

        int example = 1;
        for (String[] test : tests) {
            String s = test[0], p = test[1];
            System.out.println("Example " + example + ":");
            System.out.println("Input: s = \"" + s + "\", p = \"" + p + "\"");
            System.out.println("Output: " + solution.isMatch(s, p));
            System.out.println();
            example++;
        }
    }
}
