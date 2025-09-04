import java.util.*;

public class lt_40_CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 排序便於剪枝與去重
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝
            if (candidates[i] > target) break;
            // 去重：同層相同元素只使用一次
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, res); // 不重複使用元素
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(combinationSum2(candidates, target));
        // [[1,1,6],[1,2,5],[1,7],[2,6]]

        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        System.out.println(combinationSum2(candidates2, target2));
        // [[1,2,2],[5]]
    }
}
