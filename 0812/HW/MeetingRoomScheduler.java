import java.util.*;

public class MeetingRoomScheduler {

    // Part 1: 最少需要的會議室數量
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // 依開始時間排序

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存結束時間
        for (int[] meeting : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll(); // 可以重用會議室
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    // Part 2: 只有1個會議室時，最大總會議時間 (Weighted Interval Scheduling)
    public static int maxTotalTimeWithLimitedRooms(int[][] intervals, int rooms) {
        if (rooms <= 0 || intervals == null || intervals.length == 0) return 0;

        if (rooms == 1) {
            return weightedIntervalScheduling(intervals);
        }

        // N > 1 的版本這裡暫時不寫完整，因為需要多機排程 DP，稍後可擴充
        throw new UnsupportedOperationException("多會議室版本尚未實作");
    }

    // Weighted Interval Scheduling for N=1
    private static int weightedIntervalScheduling(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1])); // 按結束時間排序
        int n = intervals.length;
        int[] dp = new int[n];
        int[] p = new int[n]; // p[i] = 最後一個與 i 不衝突的會議 index

        // 預處理 p[i]
        for (int i = 0; i < n; i++) {
            p[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (intervals[j][1] <= intervals[i][0]) {
                    p[i] = j;
                    break;
                }
            }
        }

        // dp[i] = 前 i 個會議的最大總時間
        for (int i = 0; i < n; i++) {
            int include = (intervals[i][1] - intervals[i][0]) + (p[i] != -1 ? dp[p[i]] : 0);
            int exclude = (i > 0 ? dp[i - 1] : 0);
            dp[i] = Math.max(include, exclude);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] m1 = {{0,30},{5,10},{15,20}};
        int[][] m2 = {{9,10},{4,9},{4,17}};
        int[][] m3 = {{1,5},{8,9},{8,9}};
        int[][] m4 = {{1,4},{2,3},{4,6}};

        System.out.println("Min rooms m1: " + minMeetingRooms(m1)); // 2
        System.out.println("Min rooms m2: " + minMeetingRooms(m2)); // 2
        System.out.println("Min rooms m3: " + minMeetingRooms(m3)); // 2

        System.out.println("Max total time (1 room, m4): " + maxTotalTimeWithLimitedRooms(m4, 1)); // 5
    }
}
