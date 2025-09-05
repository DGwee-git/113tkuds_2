import java.util.*;

public class LC39_CombinationSum_PPE {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀取輸入
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        // 排序方便回溯（確保升序）
        Arrays.sort(candidates);

        List<List<Integer>> results = combinationSum(candidates, target);

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

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            path.add(candidates[i]);
            // I 版：同一元素可重複使用 → 傳 i
            backtrack(candidates, remain - candidates[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }
}
