import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length == 0 || k == 0) return 0;

        List<Integer> profits = new ArrayList<>();
        int n = prices.length;
        int i = 0;

        // 找出所有可獲利的交易
        while (i < n - 1) {
            // 找局部低點（買入點）
            while (i < n - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            int buy = prices[i];

            // 找局部高點（賣出點）
            while (i < n - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            int sell = prices[i];

            if (sell > buy) {
                profits.add(sell - buy);
            }
        }

        // 用 Max Heap 選出最大的 k 筆利潤
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int total = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            total += maxHeap.poll();
            k--;
        }

        return total;
    }

    public static void main(String[] args) {
        int[] prices1 = {2, 4, 1};
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int[] prices3 = {1, 2, 3, 4, 5};

        System.out.println(maxProfit(prices1, 2)); // 2
        System.out.println(maxProfit(prices2, 2)); // 7
        System.out.println(maxProfit(prices3, 2)); // 4
    }
}
