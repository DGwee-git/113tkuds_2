import java.util.*;

// 封裝時間表項目的類別
class TimeEntry {
    int time;       // 到站時間
    int listIndex;  // 來自哪條支線
    int position;   // 在該支線中的位置
    
    TimeEntry(int time, int listIndex, int position) {
        this.time = time;
        this.listIndex = listIndex;
        this.position = position;
    }
}

public class M12_MergeKTimeTables {
    
    // 使用Min-Heap合併K個已排序的時刻表
    public static List<Integer> mergeKTimeTables(List<List<Integer>> timeTables) {
        List<Integer> result = new ArrayList<>();
        
        // 建立Min-Heap，按時間排序
        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>((a, b) -> {
            // 主要按時間排序
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            }
            // 時間相同時，按支線索引排序（保持穩定性）
            return Integer.compare(a.listIndex, b.listIndex);
        });
        
        // 將每個時刻表的第一個元素加入堆中
        for (int i = 0; i < timeTables.size(); i++) {
            List<Integer> timeTable = timeTables.get(i);
            if (!timeTable.isEmpty()) {
                minHeap.offer(new TimeEntry(timeTable.get(0), i, 0));
            }
        }
        
        // 持續從堆中取出最小時間，並加入下一個時間
        while (!minHeap.isEmpty()) {
            TimeEntry current = minHeap.poll();
            result.add(current.time);
            
            // 如果該支線還有下一個時間，加入堆中
            List<Integer> currentList = timeTables.get(current.listIndex);
            int nextPosition = current.position + 1;
            
            if (nextPosition < currentList.size()) {
                int nextTime = currentList.get(nextPosition);
                minHeap.offer(new TimeEntry(nextTime, current.listIndex, nextPosition));
            }
        }
        
        return result;
    }
    
    // 替代實現：使用多指標法（不使用堆）
    public static List<Integer> mergeKTimeTablesWithPointers(List<List<Integer>> timeTables) {
        List<Integer> result = new ArrayList<>();
        int k = timeTables.size();
        int[] pointers = new int[k]; // 每個支線的當前指標
        
        while (true) {
            int minTime = Integer.MAX_VALUE;
            int minIndex = -1;
            boolean hasNext = false;
            
            // 找出所有支線中最小的時間
            for (int i = 0; i < k; i++) {
                if (pointers[i] < timeTables.get(i).size()) {
                    hasNext = true;
                    int currentTime = timeTables.get(i).get(pointers[i]);
                    if (currentTime < minTime) {
                        minTime = currentTime;
                        minIndex = i;
                    }
                }
            }
            
            if (!hasNext) {
                break; // 所有支線都處理完畢
            }
            
            result.add(minTime);
            pointers[minIndex]++; // 移動對應支線的指標
        }
        
        return result;
    }
    
    // 將時間字符串(HH:mm)轉換為分鐘數
    public static int timeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    // 將分鐘數轉換回時間字符串(HH:mm)
    public static String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
    
    // 處理時間格式輸入的版本
    public static List<String> mergeKTimeTablesWithFormat(List<List<String>> timeTablesStr) {
        // 轉換為分鐘格式
        List<List<Integer>> timeTables = new ArrayList<>();
        for (List<String> timeTable : timeTablesStr) {
            List<Integer> convertedTable = new ArrayList<>();
            for (String timeStr : timeTable) {
                convertedTable.add(timeToMinutes(timeStr));
            }
            timeTables.add(convertedTable);
        }
        
        // 合併時刻表
        List<Integer> mergedMinutes = mergeKTimeTables(timeTables);
        
        // 轉換回時間格式
        List<String> result = new ArrayList<>();
        for (int minutes : mergedMinutes) {
            result.add(minutesToTime(minutes));
        }
        
        return result;
    }
    
    // 輔助方法：將結果轉換為字串
    public static String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }
    
    // 驗證結果是否正確排序
    public static boolean isValidMerge(List<Integer> result) {
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) < result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入支線數量: ");
        // 讀取輸入
        int k = scanner.nextInt();
        
        List<List<Integer>> timeTables = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            System.out.print("請輸入第 " + (i+1) + " 條支線的時刻數量: ");
            int len = scanner.nextInt();
            
            System.out.print("請輸入第 " + (i+1) + " 條支線的時刻 (用空格分隔): ");
            List<Integer> timeTable = new ArrayList<>();
            
            for (int j = 0; j < len; j++) {
                timeTable.add(scanner.nextInt());
            }
            
            timeTables.add(timeTable);
        }
        
        // 合併時刻表
        List<Integer> mergedResult = mergeKTimeTables(timeTables);
        
        // 輸出結果
        System.out.println(listToString(mergedResult));
        
        scanner.close();
    }
    
    // 測試用的靜態方法
    public static void runTest() {
        // 測試範例
        List<List<Integer>> testTables = new ArrayList<>();
        testTables.add(Arrays.asList(10, 40, 90));
        testTables.add(Arrays.asList(15, 35));
        testTables.add(Arrays.asList(20, 25, 30, 100));
        
        List<Integer> result = mergeKTimeTables(testTables);
        System.out.println("Test Result: " + listToString(result));
        System.out.println("Is Valid: " + isValidMerge(result));
        
        // 測試時間格式
        List<List<String>> testTablesStr = new ArrayList<>();
        testTablesStr.add(Arrays.asList("08:10", "08:40", "09:30"));
        testTablesStr.add(Arrays.asList("08:15", "08:35"));
        testTablesStr.add(Arrays.asList("08:20", "08:25", "08:30", "10:40"));
        
        List<String> resultStr = mergeKTimeTablesWithFormat(testTablesStr);
        System.out.println("Time Format Result: " + String.join(" ", resultStr));
    }
}

