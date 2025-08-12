import java.util.*;

public class MultiLevelCacheSystem {
    static class CacheEntry {
        int key;
        String value;
        int freq;
        long timestamp;
        int level;

        CacheEntry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
            this.level = level;
        }
    }

    static class CacheLevel {
        int capacity;
        PriorityQueue<CacheEntry> pq;
        Map<Integer, CacheEntry> map;

        CacheLevel(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.pq = new PriorityQueue<>((a, b) -> {
                if (a.freq != b.freq) return a.freq - b.freq;
                return Long.compare(a.timestamp, b.timestamp);
            });
        }
    }

    private CacheLevel L1, L2, L3;
    private final int[] COST = {0, 1, 3, 10}; // index = level

    public MultiLevelCacheSystem() {
        L1 = new CacheLevel(2);
        L2 = new CacheLevel(5);
        L3 = new CacheLevel(10);
    }

    public String get(int key) {
        CacheEntry entry = findEntry(key);
        if (entry == null) return null;

        entry.freq++;
        entry.timestamp = System.nanoTime();
        promote(entry); // 看是否上移層級
        return entry.value;
    }

    public void put(int key, String value) {
        CacheEntry entry = findEntry(key);
        if (entry != null) {
            entry.value = value;
            entry.freq++;
            entry.timestamp = System.nanoTime();
            promote(entry);
            return;
        }
        insertToLevel(new CacheEntry(key, value, 1));
    }

    private CacheEntry findEntry(int key) {
        if (L1.map.containsKey(key)) return L1.map.get(key);
        if (L2.map.containsKey(key)) return L2.map.get(key);
        if (L3.map.containsKey(key)) return L3.map.get(key);
        return null;
    }

    private void insertToLevel(CacheEntry entry) {
        CacheLevel target = getLevel(entry.level);
        if (target.map.size() >= target.capacity) {
            evict(target);
        }
        target.map.put(entry.key, entry);
        target.pq.offer(entry);
    }

    private void evict(CacheLevel level) {
        CacheEntry evicted = level.pq.poll();
        if (evicted != null) {
            level.map.remove(evicted.key);
            if (evicted.level < 3) {
                evicted.level++;
                insertToLevel(evicted);
            }
        }
    }

    private void promote(CacheEntry entry) {
        if (entry.level > 1 && entry.freq >= 3) {
            removeFromLevel(entry.level, entry.key);
            entry.level--;
            insertToLevel(entry);
        }
    }

    private void removeFromLevel(int levelNum, int key) {
        CacheLevel level = getLevel(levelNum);
        CacheEntry entry = level.map.remove(key);
        if (entry != null) {
            level.pq.remove(entry);
        }
    }

    private CacheLevel getLevel(int levelNum) {
        if (levelNum == 1) return L1;
        if (levelNum == 2) return L2;
        return L3;
    }

    public void printStatus() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C"); // L1滿，淘汰到L2
        cache.printStatus();

        cache.get(1);
        cache.get(1);
        cache.get(2); // 1的freq高，會升到L1
        cache.printStatus();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printStatus();
    }
}
