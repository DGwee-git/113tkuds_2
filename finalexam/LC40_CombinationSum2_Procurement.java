import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        // 排序方便處理重複
        Arrays.sort(candidates);

        List<List<Integer>> results = combinationSum2(candidates, target);

        // 輸出所有組合
        for (List<Integer> comb : results) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print(comb.get(i));
                if (i < comb.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }

        sc.close();
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int remain, int start,
                                  List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < candidates.length; i++) {
            // II 版：同層跳過重複數值
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            // II 版：每個數字只能用一次 → 傳 i+1
            backtrack(candidates, remain - candidates[i], i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
