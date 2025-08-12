class CacheEntry {
    int key;
    String value;
    int freq;        // 存取頻率
    long timestamp;  // 最近存取時間（LRU 用）
    int level;       // 所在層級（1, 2, 3）

    CacheEntry(int key, String value, int level) {
        this.key = key;
        this.value = value;
        this.freq = 1;
        this.timestamp = System.nanoTime();
        this.level = level;
    }
}
