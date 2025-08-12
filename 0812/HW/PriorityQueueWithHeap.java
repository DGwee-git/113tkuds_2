import java.util.*;

// 任務類
class Task {
    String name;
    int priority;
    long timestamp; // 用來保持 FIFO 順序

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return name + " (優先級: " + priority + ")";
    }
}

public class PriorityQueueWithHeap {
    private PriorityQueue<Task> heap;
    private long timeCounter; // 時間計數器，模擬加入順序

    public PriorityQueueWithHeap() {
        // 自訂比較器：優先級大 → 優先，優先級相同則時間早的優先
        heap = new PriorityQueue<>((a, b) -> {
            if (b.priority != a.priority) {
                return b.priority - a.priority;
            } else {
                return Long.compare(a.timestamp, b.timestamp);
            }
        });
        timeCounter = 0;
    }

    // 添加任務
    public void addTask(String name, int priority) {
        heap.offer(new Task(name, priority, timeCounter++));
    }

    // 執行最高優先級任務
    public Task executeNext() {
        if (heap.isEmpty()) {
            System.out.println("沒有任務可執行");
            return null;
        }
        return heap.poll();
    }

    // 查看下一個任務
    public Task peek() {
        return heap.peek();
    }

    // 修改任務優先級（需要重建 heap）
    public void changePriority(String name, int newPriority) {
        List<Task> temp = new ArrayList<>();
        boolean found = false;

        while (!heap.isEmpty()) {
            Task t = heap.poll();
            if (t.name.equals(name)) {
                t.priority = newPriority;
                found = true;
            }
            temp.add(t);
        }

        heap.addAll(temp);

        if (!found) {
            System.out.println("找不到任務: " + name);
        }
    }

    // 測試
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個要執行的任務: " + pq.peek());
        System.out.println("執行: " + pq.executeNext());
        System.out.println("執行: " + pq.executeNext());
        System.out.println("執行: " + pq.executeNext());
    }
}
